package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.BasicInformationSection;
import fon.njt.cvbuilderapi.model.EducationSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationSectionRepository   extends JpaRepository<EducationSection, Long> {
}
