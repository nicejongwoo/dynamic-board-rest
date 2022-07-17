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
        checkDuplCode(request);
        //게시판 존재 체크
        Board board = checkBoard(request);

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
        //카테고리 존재 체크
        Category category = checkCategory(id);
        CategoryResponse response = CategoryDtoMapper.MAPPER.toDto(category);
        return response;
    }

    @Transactional
    @Override
    public void edit(Long id, CategoryRequest request) {
        //카테고리 코드 중복 체크
        checkDuplCode(request);
        //카테고리 존재 체크
        Category category = checkCategory(id);
        //게시판 존재 체크
        Board board = checkBoard(request);
        //업데이트
        category.updateCategory(request.getName(), request.getCode(), board);
    }

    private Board checkBoard(CategoryRequest request) {
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(
                () -> new CustomApiException("게시판이 없습니다.", HttpStatus.NOT_FOUND)
        );
        return board;
    }

    private Category checkCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new CustomApiException("카테고리가 없습니다.", HttpStatus.NOT_FOUND)
        );
        return category;
    }

    private void checkDuplCode(CategoryRequest request) {
        if (categoryRepository.existsByCode(request.getCode())) throw new CustomApiException("카테고리 코드가 이미 있습니다.", HttpStatus.BAD_REQUEST);
    }
}
