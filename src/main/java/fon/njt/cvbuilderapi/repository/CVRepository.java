package fon.njt.cvbuilderapi.repository;

import fon.njt.cvbuilderapi.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CVRepository  extends JpaRepository<CV, Long> {

}
