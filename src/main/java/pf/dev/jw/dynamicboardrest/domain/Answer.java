package pf.dev.jw.dynamicboardrest.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Answer(String content, Article article) {
        this.content = content;
        this.article = article;
    }

    public void editAnswerContent(String content) {
        this.content = content;
    }
}
