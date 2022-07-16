package pf.dev.jw.dynamicboardrest.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardRequest {

    @Pattern(regexp = "^[a-z]*$", message = "영문만 가능합니다.")
    @Size(min = 1, max = 50, message = "최소 1자에서 최대 50자 이하로 입력 가능합니다.")
    private String code;

    @Pattern(regexp = "^[가-힣]*$", message = "한글만 가능합니다.")
    @Size(min = 1, max = 50, message = "최소 1자에서 최대 50자 이하로 입력 가능합니다.")
    private String name;

    @NotBlank
    private String skin;

    private Boolean secretEnable;
    private Boolean attachmentEnable;
    private Boolean thumbnailEnable;
    private Boolean categoryEnable;
    private Boolean notificationEnable;
    private Boolean commentEnable;
    private Boolean answerEnable;
    private Boolean available;

}
