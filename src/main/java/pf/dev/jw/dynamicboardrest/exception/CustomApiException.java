package pf.dev.jw.dynamicboardrest.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomApiException extends RuntimeException{

    private HttpStatus httpStatus;

    public CustomApiException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
