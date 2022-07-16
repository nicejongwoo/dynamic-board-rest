package pf.dev.jw.dynamicboardrest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pf.dev.jw.dynamicboardrest.controller.dto.request.BoardRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.BoardSearch;

public interface BoardService {
    Long register(BoardRequest request);

    Page<BoardListResponse> getList(BoardSearch search, Pageable pageable);

    BoardResponse getOne(Long id);

    void edit(Long id, BoardRequest request);
}
