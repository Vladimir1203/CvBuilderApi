package fon.njt.cvbuilderapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvId;
    private String testName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "basicId", name = "basicId",
            foreignKey = @ForeignKey(name = "basicId_fk"))
    private BasicInformationSection basicInformationSection;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "workExperienceId", name = "workExperienceId",
            foreignKey = @ForeignKey(name = "workExperienceId_fk"))
    private WorkExperienceSection workExperienceSection;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "educationId", name = "educationId",
            foreignKey = @ForeignKey(name = "educationId_fk"))
    private EducationSection educationSection;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "interestsId", name = "interestsId",
            foreignKey = @ForeignKey(name = "interestsId_fk"))
    private InterestsSection interestsSection;
}
