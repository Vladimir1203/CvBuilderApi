package fon.njt.cvbuilderapi.service;

import fon.njt.cvbuilderapi.exceptions.OptionalTemplateNotFoundException;
import fon.njt.cvbuilderapi.model.OptionalEntity;
import fon.njt.cvbuilderapi.model.OptionalSection;
import fon.njt.cvbuilderapi.model.OptionalTemplate;
import fon.njt.cvbuilderapi.repository.CustomTemplateRepository;
import fon.njt.cvbuilderapi.repository.OptionalEntityRepository;
import fon.njt.cvbuilderapi.repository.TemplateAllSectionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomTemplateService {

    private final CustomTemplateRepository customTemplateRepository;
    private final OptionalEntityRepository optionalEntityRepository;
    private final TemplateAllSectionsRepository templateAllSectionsRepository;

    @Autowired
    public CustomTemplateService(CustomTemplateRepository customTemplateRepository, OptionalEntityRepository optionalEntityRepository, TemplateAllSectionsRepository templateAllSectionsRepository) {
        this.customTemplateRepository = customTemplateRepository;
        this.optionalEntityRepository = optionalEntityRepository;
        this.templateAllSectionsRepository = templateAllSectionsRepository;
    }

    public OptionalSection findTemplateById(Long id) {
        return (OptionalSection) customTemplateRepository.findByOptionalTemplateId(id)
                .orElseThrow(() -> new OptionalTemplateNotFoundException("Template by id " + id + " was not found"));
    }

    public OptionalSection addTemplate(OptionalSection optionalSection) {

        customTemplateRepository.save(optionalSection);
        for(int i = 0; i < optionalSection.getOptionals().size(); i++){
            optionalEntityRepository.save(optionalSection.getOptionals().get(i));
        }

        return optionalSection;
    }

    public List<OptionalSection> addTemplateList(List<OptionalSection> optionalSection) {
        for(int j = 0; j < optionalSection.size(); j++) {
            OptionalSection optionalSection1 = customTemplateRepository.save(optionalSection.get(j));
            for (int i = 0; i < optionalSection.get(j).getOptionals().size(); i++) {
                OptionalSection ot = new OptionalSection();
                optionalSection.get(j).getOptionals().get(i).setOptionalSection(ot);
                optionalSection.get(j).getOptionals().get(i).getOptionalSection().setOptionalTemplateId(optionalSection1.getOptionalTemplateId());
                optionalEntityRepository.save(optionalSection.get(j).getOptionals().get(i));
            }
        }

        return optionalSection;
    }

    public OptionalSection updateTemplate(OptionalSection optionalSection) {
        return customTemplateRepository.save(optionalSection);
    }

    public void deleteTemplate(Long id) {
        customTemplateRepository.deleteByOptionalTemplateId(id);
    }

    public List<OptionalSection> findAllTemplates() {
        List<OptionalSection> optionalSections = customTemplateRepository.findAll();
        List<OptionalEntity> optionalEntities  = optionalEntityRepository.findAll();

        for(int i = 0; i < optionalSections.size(); i++){
            List<OptionalEntity> optionals = new ArrayList<>();
            for(int j = 0; j < optionalEntities.size(); j++){
                if(optionalSections.get(i).getOptionalTemplateId() == optionalEntities.get(j).getOptionalSection().getOptionalTemplateId()){
                    optionalEntities.get(j).getOptionalSection().setOptionals(new ArrayList<>());
                    optionals.add(optionalEntities.get(j));
                }
            }
            optionalSections.get(i).setOptionals(optionals);
        }

        return optionalSections;
    }

    public OptionalTemplate addTemplateAllSections(OptionalTemplate template) {
        OptionalTemplate templateNew = templateAllSectionsRepository.save(template);
        for(int i = 0; i < template.getOptionalSections().size(); i++){
            template.getOptionalSections().get(i).setOptionalTemplate(new OptionalTemplate());
            template.getOptionalSections().get(i).getOptionalTemplate().setTemplateAllSectionsId(templateNew.getTemplateAllSectionsId());
            OptionalSection optionalSection = customTemplateRepository.save(template.getOptionalSections().get(i));
            for(int j = 0; j < template.getOptionalSections().get(i).getOptionals().size(); j++){
                template.getOptionalSections().get(j).getOptionals().get(i).setOptionalSection(new OptionalSection());
                template.getOptionalSections().get(j).getOptionals().get(i).getOptionalSection().setOptionalTemplateId(optionalSection.getOptionalTemplateId());
                optionalEntityRepository.save(template.getOptionalSections().get(j).getOptionals().get(i));
            }
        }
        return templateNew;
    }
}
