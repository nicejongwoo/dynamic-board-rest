package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.Article;
import pf.dev.jw.dynamicboardrest.repository.custom.ArticleRepositoryCustom;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
}
