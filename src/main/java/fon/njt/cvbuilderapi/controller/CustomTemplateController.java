package fon.njt.cvbuilderapi.controller;

import fon.njt.cvbuilderapi.model.OptionalSection;
import fon.njt.cvbuilderapi.model.OptionalTemplate;
import fon.njt.cvbuilderapi.model.Template;
import fon.njt.cvbuilderapi.service.CustomTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("custom/")
public class CustomTemplateController {
    private final CustomTemplateService customTemplateService;


    public CustomTemplateController(CustomTemplateService customTemplateService) {
        this.customTemplateService = customTemplateService;
    }

    @PostMapping("/saveTemplate")
    public ResponseEntity<OptionalTemplate> addTemplate(@RequestBody OptionalTemplate template){
        OptionalTemplate newTemplate = customTemplateService.addTemplate(template);
        return new ResponseEntity<OptionalTemplate>(newTemplate, HttpStatus.CREATED);
    }
//
//    @GetMapping("/getTemplateList")
//    public ResponseEntity<List<OptionalSection>> getTemplateList(){
//        List<OptionalSection> templates = customTemplateService.findAllTemplates();
//        return new ResponseEntity<>(templates, HttpStatus.OK);
//    }

    @GetMapping("/getAllTemplates")
    public ResponseEntity<List<OptionalTemplate>> getTemplates(){
        List<OptionalTemplate> templates = customTemplateService.findAllOptionalTemplates();
        return new ResponseEntity<List<OptionalTemplate>>(templates, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTemplate/{id}")
    public ResponseEntity<?> deleteTemplate(@PathVariable("id") Long id){
        customTemplateService.deleteOptionalTemplateById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

