package pf.dev.jw.dynamicboardrest.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.BoardSearch;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.domain.QBoard;
import pf.dev.jw.dynamicboardrest.repository.custom.BoardRepositoryCustom;

import javax.persistence.EntityManager;

import java.util.List;

import static org.springframework.util.StringUtils.*;
import static pf.dev.jw.dynamicboardrest.domain.QBoard.*;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<BoardListResponse> search(BoardSearch search, Pageable pageable) {
        JPAQuery<BoardListResponse> query = queryFactory
                .select(
                        Projections.fields(
                                BoardListResponse.class,
                                board.id,
                                board.code,
                                board.name,
                                board.skin,
                                board.available
                        )
                )
                .from(board)
                .where(
                        keywordContains(search.getType(), search.getKeyword())
                );

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            for (Sort.Order order : pageable.getSort()) {
                PathBuilder builder = new PathBuilder(board.getType(), board.getMetadata());
                query.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC, builder.get(order.getProperty())));
            }
        }

        List<BoardListResponse> content = query.fetch();

        JPAQuery<Board> countQuery = queryFactory
                .select(board)
                .from(board)
                .where(
                        keywordContains(search.getType(), search.getKeyword())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private Predicate keywordContains(String type, String keyword) {

        if (hasText(type)) {
            if (hasText(keyword)) {
                switch (type) {
                    case "name":
                        return board.name.contains(keyword);
                    default:
                        return null;
                }
            }
        }

        return null;
    }


}
