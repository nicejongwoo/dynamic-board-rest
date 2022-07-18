package pf.dev.jw.dynamicboardrest.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pf.dev.jw.dynamicboardrest.controller.dto.request.ArticleRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    private Long thumbnailId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<Answer> answers = new ArrayList<>();

//    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
//    private Set<Comment> comments;

    @Builder
    public Article(
            String title,
            String content,
            boolean secret,
            boolean notification,
            Long thumbnailId,
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

    public void updateArticle(ArticleRequest request, Board board, Category category) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.secret = request.isSecret();
        this.notification = request.isNotification();
        this.thumbnailId = request.getThumbnailId();
        this.board = board;
        this.category = category;
    }
}
