package pf.dev.jw.dynamicboardrest.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

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
