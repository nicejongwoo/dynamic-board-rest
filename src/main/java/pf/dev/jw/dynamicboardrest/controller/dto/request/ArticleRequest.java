package pf.dev.jw.dynamicboardrest.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleRequest {

    private String title;
    private String content;
    private boolean secret;
    private boolean notification;
    private Long thumbnailId;

    private Long boardId;
    private Long categoryId;
    private Long[] fileIds;

}
