package fon.njt.cvbuilderapi.controller;

import fon.njt.cvbuilderapi.model.CV;
import fon.njt.cvbuilderapi.model.Template;
import fon.njt.cvbuilderapi.service.CVService;
import fon.njt.cvbuilderapi.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("cvs/")
public class CVController {

    private final CVService cvService;

    @Autowired
    public CVController(CVService cvService) {
        this.cvService = cvService;
    }


    @PostMapping("/add-new")
    public ResponseEntity<CV> addCV(@RequestBody CV cv){
       CV cvNew = cvService.addCV(cv);
       return new ResponseEntity<>(cvNew, HttpStatus.CREATED);
    }
}
