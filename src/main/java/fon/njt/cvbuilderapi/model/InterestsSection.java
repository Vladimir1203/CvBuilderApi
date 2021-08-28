package fon.njt.cvbuilderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class InterestsSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestsId;
    private String firstChoice;
    private String secondChoice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "cvId", name = "cvId",
            foreignKey = @ForeignKey(name = "cvId_fk4"))
    CV cv;
}
