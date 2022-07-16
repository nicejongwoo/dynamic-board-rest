package pf.dev.jw.dynamicboardrest.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pf.dev.jw.dynamicboardrest.controller.dto.response.CommonResponse;
import pf.dev.jw.dynamicboardrest.exception.CustomApiException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> handleCustomApiException(CustomApiException e) {
        return new ResponseEntity<>(CommonResponse.fail(e.getMessage()), e.getHttpStatus());
    }

}
