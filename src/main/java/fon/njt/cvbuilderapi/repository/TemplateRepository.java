package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<Template> findByTemplateId(Long templateId);
    void deleteByTemplateId(Long templateId);
}
