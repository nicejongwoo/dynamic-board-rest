package pf.dev.jw.dynamicboardrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pf.dev.jw.dynamicboardrest.controller.dto.request.ArticleRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.ArticleSearch;

public interface ArticleService {
    Long register(ArticleRequest request);

    Page<ArticleListResponse> getList(ArticleSearch search, Pageable pageable);

    ArticleResponse getOne(Long id);

    void edit(Long id, ArticleRequest request);

}
