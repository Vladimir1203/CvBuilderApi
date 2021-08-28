package fon.njt.cvbuilderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workExperienceId;
    private String jobTitle;
    private String companyName;
    private String startDate;
    private String endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "cvId", name = "cvId",
            foreignKey = @ForeignKey(name = "cvId_fk1"))
    private CV cv;

}
