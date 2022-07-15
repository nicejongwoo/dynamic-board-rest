package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
