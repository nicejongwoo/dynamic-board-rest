package pf.dev.jw.dynamicboardrest.controller.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommonResponse<T> {

    private T data;
    private Result result;
    private String message;

    public static <T> CommonResponse<T> success(T data, String message) {
        return (CommonResponse<T>) CommonResponse.builder()
                .result(Result.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }

    public static CommonResponse fail(String message) {
        return CommonResponse.builder()
                .result(Result.FAIL)
                .data(null)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> fail(String message, T data) {
        return (CommonResponse<T>) CommonResponse.builder()
                .result(Result.FAIL)
                .data(data)
                .message(message)
                .build();
    }

    public enum Result {
        SUCCESS, FAIL
    }

}
