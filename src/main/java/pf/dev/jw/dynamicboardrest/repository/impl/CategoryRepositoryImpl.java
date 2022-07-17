package pf.dev.jw.dynamicboardrest.repository.impl;

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
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.CategorySearch;
import pf.dev.jw.dynamicboardrest.domain.Category;
import pf.dev.jw.dynamicboardrest.repository.custom.CategoryRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static pf.dev.jw.dynamicboardrest.domain.QBoard.board;
import static pf.dev.jw.dynamicboardrest.domain.QCategory.category;

public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<CategoryListResponse> search(CategorySearch search, Pageable pageable) {
        JPAQuery<CategoryListResponse> query = queryFactory
                .select(
                        Projections.fields(
                                CategoryListResponse.class,
                                category.id,
                                category.code,
                                category.name,
                                board.id.as("boardId")
                        )
                )
                .from(category)
                .leftJoin(board)
                .on(category.board.id.eq(board.id))
                .where(
                        keywordContains(search.getType(), search.getKeyword())
                );

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            for (Sort.Order order : pageable.getSort()) {
                PathBuilder builder = new PathBuilder(category.getType(), category.getMetadata());
                query.orderBy(new OrderSpecifier<>(order.isAscending() ? Order.ASC : Order.DESC, builder.get(order.getProperty())));
            }
        }

        List<CategoryListResponse> content = query.fetch();

        JPAQuery<Category> countQuery = queryFactory
                .select(category)
                .from(category)
                .leftJoin(board)
                .on(category.board.id.eq(board.id))
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
                        return category.name.contains(keyword);
                    case "code":
                        return category.code.contains(keyword);
                    default:
                        return null;
                }
            }
        }

        return null;
    }

}
