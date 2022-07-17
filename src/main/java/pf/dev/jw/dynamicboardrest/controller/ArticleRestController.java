package pf.dev.jw.dynamicboardrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.request.ArticleRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.ArticleSearch;
import pf.dev.jw.dynamicboardrest.service.ArticleService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/article")
@RestController
public class ArticleRestController {

    private final ArticleService articleService;

    @PostMapping("")
    public ResponseEntity<?> register(@Valid @RequestBody ArticleRequest request) {
        Long id = articleService.register(request);
        return new ResponseEntity<>(CommonResponse.success(id, "게시글을 등록하였습니다."), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getList(ArticleSearch search, Pageable pageable) {
        Page<ArticleListResponse> page = articleService.getList(search, pageable);
        return ResponseEntity.ok(CommonResponse.success(page, ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ArticleResponse response = articleService.getOne(id);
        return ResponseEntity.ok(CommonResponse.success(response, ""));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody ArticleRequest request) {
        articleService.edit(id, request);
        return ResponseEntity.ok(CommonResponse.success(id, "게시글을 수정하였습니다."));
    }
}
