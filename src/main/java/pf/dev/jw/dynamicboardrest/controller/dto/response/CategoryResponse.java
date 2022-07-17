package pf.dev.jw.dynamicboardrest.controller.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CategoryResponse {

    private Long id;
    private String code;
    private String name;
    private BoardResponse board;

}
