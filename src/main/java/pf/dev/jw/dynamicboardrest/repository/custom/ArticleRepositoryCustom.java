package pf.dev.jw.dynamicboardrest.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.ArticleSearch;

public interface ArticleRepositoryCustom {

    Page<ArticleListResponse> search(ArticleSearch search, Pageable pageable);
}
