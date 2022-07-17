package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);
}
