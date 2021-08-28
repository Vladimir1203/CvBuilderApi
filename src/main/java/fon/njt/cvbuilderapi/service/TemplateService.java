package fon.njt.cvbuilderapi.service;


import fon.njt.cvbuilderapi.exceptions.TemplateNotFoundException;
import fon.njt.cvbuilderapi.model.Template;
import fon.njt.cvbuilderapi.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TemplateService {

    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<Template> findAllTemplates() {
        return templateRepository.findAll();
    }

    public Template findTemplateById(Long id) {
        return templateRepository.findByTemplateId(id)
                .orElseThrow(() -> new TemplateNotFoundException("Template by id " + id + " was not found"));
    }

    public Template addTemplate(Template template) {
        return templateRepository.save(template);
    }

    public Template updateTemplate(Template template) {
        return templateRepository.save(template);
    }

    public void deleteTemplate(Long id) {
        templateRepository.deleteByTemplateId(id);
    }
}
