package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.OptionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionalEntityRepository  extends JpaRepository<OptionalEntity, Long> {
    void deleteByOptionalEntityId(Long templateId);
    List<OptionalEntity> findAll();
    OptionalEntity save(OptionalEntity optionalEntity);
    <T> Optional<T> findByOptionalEntityId(Long id);

}
