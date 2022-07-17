package pf.dev.jw.dynamicboardrest.controller.dto.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pf.dev.jw.dynamicboardrest.controller.dto.request.ArticleRequest;
import pf.dev.jw.dynamicboardrest.domain.Article;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.domain.Category;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ArticleDtoMapperTest {

    @Test
    void toEntityTest() {
        ArticleRequest build = ArticleRequest.builder()
                .title("Test title")
                .content("Test content")
                .secret(false)
                .notification(false)
                .thumbnailId(null)
                .build();

        Article article = ArticleDtoMapper.MAPPER.toEntity(build, null, null);

        assertThat(article).isNotNull();
        assertThat(article.getTitle()).isEqualTo(build.getTitle());
    }

}