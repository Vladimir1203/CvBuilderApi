package fon.njt.cvbuilderapi.controller;

import fon.njt.cvbuilderapi.model.Template;
import fon.njt.cvbuilderapi.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("templates/")
public class TemplateController {
    private final TemplateService templateService;

    @Autowired
    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping("all")
    public ResponseEntity<List<Template>> getAllTemplates(){
        List<Template> templates = templateService.findAllTemplates();
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Template> getTemplateById (@PathVariable("id") Long id) {
        Template template = templateService.findTemplateById(id);
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Template> addTemplate(@RequestBody Template template){
        Template newTemplate = templateService.addTemplate(template);
        return new ResponseEntity<>(newTemplate, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Template> updateTemplate(@RequestBody Template template){
        Template updateTemplate = templateService.updateTemplate(template);
        return new ResponseEntity<>(updateTemplate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updateTemplate(@PathVariable("id") Long id){
        templateService.deleteTemplate(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
