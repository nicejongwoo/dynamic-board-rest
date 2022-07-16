package pf.dev.jw.dynamicboardrest.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> handleCustomApiException(CustomApiException e) {
        return new ResponseEntity<>(CommonResponse.fail(e.getMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<Object> list = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            data.put(error.getField(), error.getDefaultMessage());
            list.add(data);
        }

        return new ResponseEntity<>(CommonResponse.fail("유효성 검증 에러", data), HttpStatus.BAD_REQUEST);
    }
}
