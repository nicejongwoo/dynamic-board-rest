package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
