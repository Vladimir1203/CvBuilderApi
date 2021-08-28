package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.BasicInformationSection;
import fon.njt.cvbuilderapi.model.InterestsSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestsSectionRepository   extends JpaRepository<InterestsSection, Long> {
}
