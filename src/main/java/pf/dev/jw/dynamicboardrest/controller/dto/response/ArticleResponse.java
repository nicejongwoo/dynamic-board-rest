package pf.dev.jw.dynamicboardrest.controller.dto.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ArticleResponse {

    private Long id;
    private String title;
    private String content;
    private boolean secret;
    private boolean notification;
    private Integer thumbnailId;

    private BoardResponse board;
    private CategoryResponse category;
    private List<FileResponse> files;
    private List<AnswerResponse> answers;

}
