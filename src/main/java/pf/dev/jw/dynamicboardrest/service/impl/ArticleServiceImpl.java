package pf.dev.jw.dynamicboardrest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.AnswerDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.ArticleDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.BoardDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.FileDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.request.ArticleRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.AnswerResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.ArticleResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.FileResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.ArticleSearch;
import pf.dev.jw.dynamicboardrest.domain.*;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;
import pf.dev.jw.dynamicboardrest.repository.ArticleRepository;
import pf.dev.jw.dynamicboardrest.repository.BoardRepository;
import pf.dev.jw.dynamicboardrest.repository.CategoryRepository;
import pf.dev.jw.dynamicboardrest.repository.FileRepository;
import pf.dev.jw.dynamicboardrest.service.ArticleService;

import java.util.Arrays;
import java.util.List;

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
        Board board = checkBoard(request.getBoardId());
        //카테고리 확인
        Category category = checkCategory(request.getCategoryId(), board);

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
        Page<ArticleListResponse> page = articleRepository.search(search, pageable);

        for (ArticleListResponse response : page) {
            Board board = checkBoard(response.getBoardId());
            response.setBoard(BoardDtoMapper.MAPPER.toDto(board));
        }

        return page;
    }

    @Override
    public ArticleResponse getOne(Long id) {
        Article article = checkArticle(id);
        Board board = checkBoard(article.getBoard().getId());
        ArticleResponse response = ArticleDtoMapper.MAPPER.toDto(article);

        if (board.isAttachmentEnable() && article.getAttachment() != null) {
            List<FileResponse> fileResponses = FileDtoMapper.MAPPER.toDtoList(article.getAttachment().getFiles());
            response.setFiles(fileResponses);
        }

        if (board.isAnswerEnable() && article.getAnswers().size() > 0) {
            List<AnswerResponse> answerResponses = AnswerDtoMapper.MAPPER.toDtoList(article.getAnswers());
            response.setAnswers(answerResponses);
        }

        return response;
    }

    @Transactional
    @Override
    public void edit(Long id, ArticleRequest request) {
        Article article = checkArticle(id);
        Board board = checkBoard(request.getBoardId());
        Category category = checkCategory(request.getCategoryId(), board);

        //Attachment
        if (board.isAttachmentEnable()) {
            List<File> files = article.getAttachment().getFiles();
            Long[] fileIds = request.getFileIds();

            //기존에 해당 게시판에 첨부된 파일 목록 모두 첨부 초기화함
            if (files.size() > 0) {
                files.stream().forEach(file -> file.uploadAttachment(null));
            }

            //기존에 파일 첨부를 하지 않았는데 업데이트 할 때 파일 첨부를 새롭게 하는 경우 게시글의 첨부를 업데이트
            if (article.getAttachment() == null && fileIds.length > 0) {
                article.updateAttachment(new Attachment());
            }

            //파일 요청이 있을 경우, 해당 파일 아이디에 해당하는 파일 목록의 첨부를 업데이트
            if (fileIds.length > 0) {
                Arrays.stream(fileIds).forEach(fileId -> {
                    File file = fileRepository.findById(fileId).orElseThrow(
                            () -> new CustomApiException("파일이 없습니다.", HttpStatus.NOT_FOUND)
                    );
                    file.uploadAttachment(article.getAttachment());
                });
            }
        }

        article.updateArticle(request, board, category);
    }

    private Category checkCategory(Long id, Board board) {
        return board.isCategoryEnable() ? categoryRepository.findById(id).orElseThrow(
                () -> new CustomApiException("카테고리가 없습니다.", HttpStatus.NOT_FOUND)
        ) : null;
    }

    private Board checkBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomApiException("게시판이 없습니다.", HttpStatus.NOT_FOUND)
        );
        return board;
    }

    private Article checkArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new CustomApiException("게시글이 없습니다.", HttpStatus.NOT_FOUND)
        );
        return article;
    }
}
