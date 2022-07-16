package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.Board;
import pf.dev.jw.dynamicboardrest.repository.custom.BoardRepositoryCustom;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    boolean existsByCode(String code);
}
