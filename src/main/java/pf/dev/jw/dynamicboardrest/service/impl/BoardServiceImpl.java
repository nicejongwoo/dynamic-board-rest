package pf.dev.jw.dynamicboardrest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pf.dev.jw.dynamicboardrest.controller.dto.mapper.BoardDtoMapper;
import pf.dev.jw.dynamicboardrest.controller.dto.request.BoardRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.BoardSearch;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;
import pf.dev.jw.dynamicboardrest.repository.BoardRepository;
import pf.dev.jw.dynamicboardrest.service.BoardService;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    @Override
    public Long register(BoardRequest request) {
        if (boardRepository.existsByCode(request.getCode())) {
            throw new CustomApiException("게시판 코드가 중복입니다.", HttpStatus.BAD_REQUEST);
        }

        //boardRequest -> Board
        Board board = BoardDtoMapper.MAPPER.toEntity(request);
        boardRepository.save(board);
        return board.getId();
    }

    @Override
    public Page<BoardListResponse> getList(BoardSearch search, Pageable pageable) {
        Page<BoardListResponse> page = boardRepository.search(search, pageable);
        return page;
    }

    @Override
    public BoardResponse getOne(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new CustomApiException("게시판이 없습니다.", HttpStatus.NOT_FOUND));
        return BoardDtoMapper.MAPPER.toDto(board);
    }
}
