package fon.njt.cvbuilderapi.controller;

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
    public ResponseEntity<OptionalTemplate> addTemplate(@RequestBody OptionalTemplate optionalTemplate){
        OptionalTemplate newTemplate = customTemplateService.addTemplate(optionalTemplate);
        return new ResponseEntity<>(newTemplate, HttpStatus.CREATED);
    }

    @PostMapping("/saveTemplateList")
    public ResponseEntity<OptionalTemplate> addTemplateList(@RequestBody List<OptionalTemplate> optionalTemplate){
        List<OptionalTemplate> newTemplate = customTemplateService.addTemplateList(optionalTemplate);
        return new ResponseEntity<OptionalTemplate>(newTemplate.get(0), HttpStatus.CREATED);
    }

    @GetMapping("/getTemplateList")
    public ResponseEntity<List<OptionalTemplate>> getTemplateList(){
        List<OptionalTemplate> templates = customTemplateService.findAllTemplates();
        return new ResponseEntity<>(templates, HttpStatus.OK);
    }

}

