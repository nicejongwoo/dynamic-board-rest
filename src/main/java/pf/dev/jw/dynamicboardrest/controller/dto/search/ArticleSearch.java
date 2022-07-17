package pf.dev.jw.dynamicboardrest.controller.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleSearch {

    private String type;
    private String keyword;

}
