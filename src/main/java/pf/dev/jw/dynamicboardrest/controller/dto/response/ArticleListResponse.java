package pf.dev.jw.dynamicboardrest.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ArticleListResponse {

    private Long id;
    private String title;
    private boolean secret;
    private boolean notification;
    private Long thumbnailId;

    private String categoryName;

    @JsonIgnore
    private Long boardId;
    private BoardResponse board;

    private Long fileCount;

}
