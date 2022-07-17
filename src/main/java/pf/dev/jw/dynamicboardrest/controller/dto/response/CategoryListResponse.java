package pf.dev.jw.dynamicboardrest.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CategoryListResponse {
    private Long id;
    private String name;
    private String code;
    @JsonIgnore
    private Long boardId;
    private BoardResponse board;
}
