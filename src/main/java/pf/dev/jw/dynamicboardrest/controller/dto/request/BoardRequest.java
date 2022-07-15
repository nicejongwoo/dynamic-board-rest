package pf.dev.jw.dynamicboardrest.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardRequest {

    private String code;
    private String name;
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
