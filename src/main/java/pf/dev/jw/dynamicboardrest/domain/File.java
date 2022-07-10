package pf.dev.jw.dynamicboardrest.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalName;
    private String extension;
    private Long size;
    private String type;
    private String newName;

    public File() {
    }

    public File(String originalName, String extension,
                Long size, String type, String newName) {
        this.originalName = originalName;
        this.extension = extension;
        this.size = size;
        this.type = type;
        this.newName = newName;
    }
}
