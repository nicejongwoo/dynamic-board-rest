package pf.dev.jw.dynamicboardrest.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.CategorySearch;

public interface CategoryRepositoryCustom {

    Page<CategoryListResponse> search(CategorySearch search, Pageable pageable);

}
