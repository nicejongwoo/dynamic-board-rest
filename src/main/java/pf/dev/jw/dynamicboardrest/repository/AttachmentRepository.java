package pf.dev.jw.dynamicboardrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pf.dev.jw.dynamicboardrest.domain.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
