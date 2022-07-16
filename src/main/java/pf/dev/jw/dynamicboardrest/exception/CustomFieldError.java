package pf.dev.jw.dynamicboardrest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomFieldError {
    private String field;
    private String message;
}
