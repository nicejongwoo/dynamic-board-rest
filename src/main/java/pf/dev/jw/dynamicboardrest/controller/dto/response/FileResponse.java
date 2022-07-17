package pf.dev.jw.dynamicboardrest.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileResponse {

    private Long id;
    private String originalName;
    private String extension;
    private Long size;
    private String type;
    private String newName;

}
