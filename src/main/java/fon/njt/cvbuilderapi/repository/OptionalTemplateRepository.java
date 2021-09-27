package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.OptionalTemplate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OptionalTemplateRepository extends JpaRepository<OptionalTemplate, Long> {
    void deleteByTemplateAllSectionsId(Long id);
}
