package pf.dev.jw.dynamicboardrest.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileRequestDto {

    private String originalName;
    private String extension;
    private Long size;
    private String type;
    private String newName;

    public FileRequestDto() {
    }

    public FileRequestDto(String originalName, String extension,
                          Long size, String type, String newName) {
        this.originalName = originalName;
        this.extension = extension;
        this.size = size;
        this.type = type;
        this.newName = newName;
    }
}
