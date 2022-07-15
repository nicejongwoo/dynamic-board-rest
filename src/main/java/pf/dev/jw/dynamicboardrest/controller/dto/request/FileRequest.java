package pf.dev.jw.dynamicboardrest.controller.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileRequest {

    private String originalName;
    private String extension;
    private Long size;
    private String type;
    private String newName;

}
