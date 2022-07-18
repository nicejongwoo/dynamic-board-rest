package pf.dev.jw.dynamicboardrest.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Builder
    public Comment(String content, Answer answer) {
        this.content = content;
        this.answer = answer;
    }

    public void editContent(String content) {
        this.content = content;
    }
}
