package pf.dev.jw.dynamicboardrest.controller.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategorySearch {

    private String type;
    private String keyword;

}
