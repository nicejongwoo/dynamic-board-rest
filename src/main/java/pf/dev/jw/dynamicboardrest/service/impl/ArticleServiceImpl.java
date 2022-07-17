package pf.dev.jw.dynamicboardrest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.ArticleDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.request.ArticleRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.ArticleSearch;
import pf.dev.jw.dynamicboardrest.domain.*;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;
import pf.dev.jw.dynamicboardrest.repository.ArticleRepository;
import pf.dev.jw.dynamicboardrest.repository.BoardRepository;
import pf.dev.jw.dynamicboardrest.repository.CategoryRepository;
import pf.dev.jw.dynamicboardrest.repository.FileRepository;
import pf.dev.jw.dynamicboardrest.service.ArticleService;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final FileRepository fileRepository;

    @Transactional
    @Override
    public Long register(ArticleRequest request) {
        //게시판 확인
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(
                () -> new CustomApiException("게시판이 없습니다.", HttpStatus.NOT_FOUND)
        );
        //카테고리 확인
        Category category = board.isCategoryEnable() ? categoryRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new CustomApiException("카테고리가 없습니다.", HttpStatus.NOT_FOUND)
        ) : null;

        Article article = ArticleDtoMapper.MAPPER.toEntity(request, board, category);

        //fileId > 0 -> attachment new -> fileEntity attachment upload
        if (board.isAttachmentEnable() && request.getFileIds().length > 0) {
            article.updateAttachment(new Attachment());
            for (Long fileId : request.getFileIds()) {
                File file = fileRepository.findById(fileId).orElseThrow(() -> new CustomApiException("파일이 없습니다.", HttpStatus.NOT_FOUND));
                file.uploadAttachment(article.getAttachment());
            }
        }

        articleRepository.save(article);
        return article.getId();
    }

    @Override
    public Page<ArticleListResponse> getList(ArticleSearch search, Pageable pageable) {
        return null;
    }

    @Override
    public ArticleResponse getOne(Long id) {
        return null;
    }

    @Override
    public void edit(Long id, ArticleRequest request) {

    }
}
