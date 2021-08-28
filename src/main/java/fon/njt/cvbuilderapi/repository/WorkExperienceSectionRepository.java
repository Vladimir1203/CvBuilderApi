package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.CV;
import fon.njt.cvbuilderapi.model.WorkExperienceSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkExperienceSectionRepository   extends JpaRepository<WorkExperienceSection, Long> {
}
