package pf.dev.jw.dynamicboardrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.dev.jw.dynamicboardrest.controller.dto.request.BoardRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardListResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.BoardResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.controller.dto.search.BoardSearch;
import pf.dev.jw.dynamicboardrest.service.BoardService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody BoardRequest request) {
        Long id = boardService.register(request);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getList(BoardSearch search, Pageable pageable) {
        Page<BoardListResponse> page = boardService.getList(search, pageable);
        return ResponseEntity.ok(CommonResponse.success(page, ""));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        BoardResponse response = boardService.getOne(id);
        return ResponseEntity.ok(CommonResponse.success(response, ""));
    }


}
