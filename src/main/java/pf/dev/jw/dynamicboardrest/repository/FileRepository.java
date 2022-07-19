package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.File;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByAttachmentId(Long id);
}
