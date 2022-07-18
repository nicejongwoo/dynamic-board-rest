package pf.dev.jw.dynamicboardrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.dev.jw.dynamicboardrest.controller.dto.request.CommentRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.service.CommentService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
@RestController
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> register(@Valid @RequestBody CommentRequest request) {
        Long id = commentService.register(request);
        return new ResponseEntity<>(CommonResponse.success(id, "댓글을 등록하였습니다."), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody CommentRequest request) {
        commentService.edit(id, request);
        return ResponseEntity.ok(CommonResponse.success(id, "댓글을 수정하였습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(CommonResponse.success(id, "댓글을 삭제하였습니다."));
    }

}
