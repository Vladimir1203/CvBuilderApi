package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.OptionalTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomTemplateRepository extends JpaRepository<OptionalTemplate, Long> {
    void deleteByOptionalTemplateId(Long templateId);
    List<OptionalTemplate> findAll();
    OptionalTemplate save(OptionalTemplate optionalTemplate);
    <T> Optional<T> findByOptionalTemplateId(Long id);
}
