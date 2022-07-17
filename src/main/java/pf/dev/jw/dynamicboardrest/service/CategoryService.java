package pf.dev.jw.dynamicboardrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CategoryRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.CategorySearch;

public interface CategoryService {
    Long register(CategoryRequest request);

    Page<CategoryListResponse> getList(CategorySearch search, Pageable pageable);

    CategoryResponse getOne(Long id);

    void edit(Long id, CategoryRequest request);
}
