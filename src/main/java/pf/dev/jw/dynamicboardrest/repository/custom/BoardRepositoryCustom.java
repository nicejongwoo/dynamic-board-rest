package pf.dev.jw.dynamicboardrest.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.BoardSearch;

public interface BoardRepositoryCustom {

    Page<BoardListResponse> search(BoardSearch boardSearch, Pageable pageable);

}
