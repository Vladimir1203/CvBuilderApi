package fon.njt.cvbuilderapi.service;

import fon.njt.cvbuilderapi.exceptions.OptionalTemplateNotFoundException;
import fon.njt.cvbuilderapi.model.OptionalEntity;
import fon.njt.cvbuilderapi.repository.OptionalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OptionalEntityService {
    private final OptionalEntityRepository optionalEntityRepository;
    @Autowired
    public OptionalEntityService(OptionalEntityRepository optionalEntityRepository) {
        this.optionalEntityRepository = optionalEntityRepository;
    }
    public List<OptionalEntity> findAllOptionalEntities() {
        return optionalEntityRepository.findAll();
    }

    public OptionalEntity findTemplateById(Long id) {
        return (OptionalEntity) optionalEntityRepository.findByOptionalEntityId(id)
                .orElseThrow(() -> new OptionalTemplateNotFoundException("Template by id " + id + " was not found"));
    }

    public OptionalEntity addTemplate(OptionalEntity optionalEntity) {
        return optionalEntityRepository.save(optionalEntity);
    }

    public OptionalEntity updateOptionalEntity(OptionalEntity optionalEntity) {
        return optionalEntityRepository.save(optionalEntity);
    }

    public void deleteByOptionalEntityId(Long id) {
        optionalEntityRepository.deleteByOptionalEntityId(id);
    }
}
