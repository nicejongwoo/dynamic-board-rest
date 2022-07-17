package pf.dev.jw.dynamicboardrest.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private int hit;

    private boolean secret;

    private boolean notification;

    private Integer thumbnailId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    private Answer answer;

    @Builder
    public Article(
            String title,
            String content,
            boolean secret,
            boolean notification,
            Integer thumbnailId,
            Board board,
            Category category
    ) {
        this.title = title;
        this.content = content;
        this.secret = secret;
        this.notification = notification;
        this.thumbnailId = thumbnailId;
        this.board = board;
        this.category = category;
    }

    public void updateAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
