package pf.dev.jw.dynamicboardrest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.CategoryDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CategoryRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CategoryResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.CategorySearch;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.domain.Category;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;
import pf.dev.jw.dynamicboardrest.repository.BoardRepository;
import pf.dev.jw.dynamicboardrest.repository.CategoryRepository;
import pf.dev.jw.dynamicboardrest.service.CategoryService;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public Long register(CategoryRequest request) {
        //카테고리 코드 중복 체크
        if (categoryRepository.existsByCode(request.getCode())) throw new CustomApiException("카테고리 코드가 이미 있습니다.", HttpStatus.BAD_REQUEST);
        //게시판 존재 체크
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(
                () -> new CustomApiException("게시판이 없습니다.", HttpStatus.NOT_FOUND)
        );

        Category category = CategoryDtoMapper.MAPPER.toEntity(request, board);
        categoryRepository.save(category);
        return category.getId();
    }

    @Override
    public Page<CategoryListResponse> getList(CategorySearch search, Pageable pageable) {
        return null;
    }

    @Override
    public CategoryResponse getOne(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void edit(Long id, CategoryRequest request) {

    }
}