package pf.dev.jw.dynamicboardrest.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "attachment")
    private Article article;

    @OneToMany(mappedBy = "attachment")
    private List<File> files;

}
