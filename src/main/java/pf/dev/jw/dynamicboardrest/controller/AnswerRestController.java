package pf.dev.jw.dynamicboardrest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.dev.jw.dynamicboardrest.controller.dto.request.AnswerRequest;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.service.AnswerService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/answer")
@RestController
public class AnswerRestController {

    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<?> register(@Valid @RequestBody AnswerRequest request) {
        Long id = answerService.register(request);
        return new ResponseEntity<>(CommonResponse.success(id, "답변을 등록하였습니다."), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody AnswerRequest request) {
        answerService.edit(id, request);
        return ResponseEntity.ok(CommonResponse.success(id, "답변을 수정하였습니다."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        answerService.delete(id);
        return ResponseEntity.ok(CommonResponse.success(id, "답변을 삭제하였습니다."));
    }

}
