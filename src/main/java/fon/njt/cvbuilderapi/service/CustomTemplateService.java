package fon.njt.cvbuilderapi.service;

import fon.njt.cvbuilderapi.exceptions.OptionalTemplateNotFoundException;
import fon.njt.cvbuilderapi.model.OptionalEntity;
import fon.njt.cvbuilderapi.model.OptionalTemplate;
import fon.njt.cvbuilderapi.repository.CustomTemplateRepository;
import fon.njt.cvbuilderapi.repository.OptionalEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomTemplateService {

    private final CustomTemplateRepository customTemplateRepository;
    private final OptionalEntityRepository optionalEntityRepository;

    @Autowired
    public CustomTemplateService(CustomTemplateRepository customTemplateRepository, OptionalEntityRepository optionalEntityRepository) {
        this.customTemplateRepository = customTemplateRepository;
        this.optionalEntityRepository = optionalEntityRepository;
    }

    public OptionalTemplate findTemplateById(Long id) {
        return (OptionalTemplate) customTemplateRepository.findByOptionalTemplateId(id)
                .orElseThrow(() -> new OptionalTemplateNotFoundException("Template by id " + id + " was not found"));
    }

    public OptionalTemplate addTemplate(OptionalTemplate optionalTemplate) {

        customTemplateRepository.save(optionalTemplate);
        for(int i = 0; i < optionalTemplate.getOptionals().size(); i++){
            optionalEntityRepository.save(optionalTemplate.getOptionals().get(i));
        }

        return optionalTemplate;
    }

    public List<OptionalTemplate> addTemplateList(List<OptionalTemplate> optionalTemplate) {
        for(int j = 0; j < optionalTemplate.size(); j++) {
            OptionalTemplate optionalTemplate1 = customTemplateRepository.save(optionalTemplate.get(j));
            for (int i = 0; i < optionalTemplate.get(j).getOptionals().size(); i++) {
                OptionalTemplate ot = new OptionalTemplate();
                optionalTemplate.get(j).getOptionals().get(i).setOptionalTemplate(ot);
                optionalTemplate.get(j).getOptionals().get(i).getOptionalTemplate().setOptionalTemplateId(optionalTemplate1.getOptionalTemplateId());
                optionalEntityRepository.save(optionalTemplate.get(j).getOptionals().get(i));
            }
        }

        return optionalTemplate;
    }

    public OptionalTemplate updateTemplate(OptionalTemplate optionalTemplate) {
        return customTemplateRepository.save(optionalTemplate);
    }

    public void deleteTemplate(Long id) {
        customTemplateRepository.deleteByOptionalTemplateId(id);
    }

    public List<OptionalTemplate> findAllTemplates() {
        List<OptionalTemplate> optionalTemplates = customTemplateRepository.findAll();
        for(int i = 0; i < optionalTemplates.size(); i++){
            optionalTemplates.get(i).setOptionals(optionalEntityRepository.findByOptionalTemplate(optionalTemplates.get(i).getOptionalTemplateId()));
        }

        return optionalTemplates;
    }
}
