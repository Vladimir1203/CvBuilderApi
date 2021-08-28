package fon.njt.cvbuilderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EducationSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long educationId;
    private String schoolName;
    private String startDate;
    private String endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "cvId", name = "cvId",
            foreignKey = @ForeignKey(name = "cvId_fk3"))
    CV cv;
}
