package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
