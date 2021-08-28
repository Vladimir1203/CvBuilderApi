package fon.njt.cvbuilderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BasicInformationSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basicId;
    private String name;
    private String surname;
    private String email;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "cvId", name = "cvId",
            foreignKey = @ForeignKey(name = "cvId_fk"))
    CV cv;
}
