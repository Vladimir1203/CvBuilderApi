package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.OptionalSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomTemplateRepository extends JpaRepository<OptionalSection, Long> {
    void deleteByOptionalTemplateId(Long templateId);
    List<OptionalSection> findAll();
    OptionalSection save(OptionalSection optionalSection);
    <T> Optional<T> findByOptionalTemplateId(Long id);
}
