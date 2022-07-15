package pf.dev.jw.dynamicboardrest.service;

import pf.dev.jw.dynamicboardrest.controller.dto.request.BoardRequest;

public interface BoardService {
    Long register(BoardRequest request);
}
