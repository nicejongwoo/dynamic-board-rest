package pf.dev.jw.dynamicboardrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CategoryRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.CategorySearch;
import pf.dev.jw.dynamicboardrest.service.CategoryService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/category")
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> register(@Valid @RequestBody CategoryRequest request) {
        Long id = categoryService.register(request);
        return new ResponseEntity<>(CommonResponse.success(id, "카테고리를 등록하였습니다."), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getList(CategorySearch search, Pageable pageable) {
        Page<CategoryListResponse> page = categoryService.getList(search, pageable);
        return ResponseEntity.ok(CommonResponse.success(page, ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        CategoryResponse response = categoryService.getOne(id);
        return ResponseEntity.ok(CommonResponse.success(response, ""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        categoryService.edit(id, request);
        return ResponseEntity.ok(CommonResponse.success(id, "카테고리를 수정하였습니다."));
    }

}
