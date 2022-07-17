package pf.dev.jw.dynamicboardrest.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {

    private Long id;
    private String code;
    private String name;
    private BoardResponse board;

}
