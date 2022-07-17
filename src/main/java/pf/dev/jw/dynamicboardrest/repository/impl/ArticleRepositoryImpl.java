package pf.dev.jw.dynamicboardrest.repository.impl;

import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.ArticleSearch;
import pf.dev.jw.dynamicboardrest.domain.Article;
import pf.dev.jw.dynamicboardrest.repository.custom.ArticleRepositoryCustom;

import javax.persistence.EntityManager;
import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static org.springframework.util.StringUtils.hasText;
import static pf.dev.jw.dynamicboardrest.domain.QArticle.article;
import static pf.dev.jw.dynamicboardrest.domain.QBoard.board;
import static pf.dev.jw.dynamicboardrest.domain.QCategory.category;
import static pf.dev.jw.dynamicboardrest.domain.QFile.file;

@Slf4j
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<ArticleListResponse> search(ArticleSearch search, Pageable pageable) {

        JPAQuery<ArticleListResponse> query = queryFactory
                .select(
                        Projections.fields(
                                ArticleListResponse.class,
                                article.id,
                                article.title,
                                article.secret,
                                article.notification,
                                article.thumbnailId,
                                category.name.as("categoryName"),
                                board.id.as("boardId"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(count(file.id))
                                                .from(file)
                                                .where(article.attachment.id.eq(file.attachment.id)),
                                        "fileCount")
                        )
                )
                .from(article)
                .leftJoin(board)
                .on(article.board.id.eq(board.id))
                .leftJoin(category)
                .on(article.category.id.eq(category.id))
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

        List<ArticleListResponse> content = query.fetch();

        JPAQuery<Article> countQuery = queryFactory
                .select(article)
                .from(article)
                .leftJoin(board)
                .on(article.board.id.eq(board.id))
                .leftJoin(category)
                .on(article.category.id.eq(category.id))
                .where(
                        keywordContains(search.getType(), search.getKeyword())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private Predicate keywordContains(String type, String keyword) {

        if (hasText(type)) {
            if (hasText(keyword)) {
                switch (type) {
                    case "title":
                        return article.title.contains(keyword);
                    case "content":
                        return article.content.contains(keyword);
                    default:
                        return null;
                }
            }
        }

        return null;
    }
}
