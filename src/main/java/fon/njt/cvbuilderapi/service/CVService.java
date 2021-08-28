package fon.njt.cvbuilderapi.service;

import fon.njt.cvbuilderapi.model.CV;
import fon.njt.cvbuilderapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CVService {

    private final CVRepository cvRepository;
    private final BasicInformationSectionRepository basicInformationSectionRepository;
    private final WorkExperienceSectionRepository workExperienceSectionRepository;
    private final EducationSectionRepository educationSectionRepository;
    private final InterestsSectionRepository interestsSectionRepository;

    @Autowired
    public CVService(CVRepository cvRepository, BasicInformationSectionRepository basicInformationSectionRepository, WorkExperienceSectionRepository workExperienceSectionRepository, EducationSectionRepository educationSectionRepository, InterestsSectionRepository interestsSectionRepository) {
        this.cvRepository = cvRepository;
        this.basicInformationSectionRepository = basicInformationSectionRepository;
        this.workExperienceSectionRepository = workExperienceSectionRepository;
        this.educationSectionRepository = educationSectionRepository;
        this.interestsSectionRepository = interestsSectionRepository;
    }


    public CV addCV(CV cv) {
//        System.out.println("DOVDE SVE RADI *****************************");
//        basicInformationSectionRepository.save(cv.getBasicInformationSection());
//        workExperienceSectionRepository.save(cv.getWorkExperienceSection());
//
//        return cvRepository.save(cv);
        CV newCv = cvRepository.save(cv);
        //System.out.println(newCv.getCvId() + " " + cv.getBasicInformationSection().getCv().getCvId());
        cv.getBasicInformationSection().setCv(new CV());
        cv.getWorkExperienceSection().setCv(new CV());
        cv.getEducationSection().setCv(new CV());
        cv.getInterestsSection().setCv(new CV());
        cv.getBasicInformationSection().getCv().setCvId(newCv.getCvId());
        cv.getWorkExperienceSection().getCv().setCvId(newCv.getCvId());
        cv.getEducationSection().getCv().setCvId(newCv.getCvId());
        cv.getInterestsSection().getCv().setCvId(newCv.getCvId());

        basicInformationSectionRepository.save(cv.getBasicInformationSection());
        workExperienceSectionRepository.save(cv.getWorkExperienceSection());
        educationSectionRepository.save(cv.getEducationSection());
        interestsSectionRepository.save(cv.getInterestsSection());

        System.out.println("TOTAL SUCCESS!");
        return newCv;

        //sacuvaj CV samo (samo kolona id i naziv, eventualno date created) saveCV
        //pronadji ga u bazi findCV(id)
        //postavi id od CVa na fk za sekcije
        //sacuvaj sekcije pojedinacno
    }
}
