package pf.dev.jw.dynamicboardrest.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardListResponse {
    private Long id;
    private String code;
    private String name;
    private String skin;
    private boolean available;
}
