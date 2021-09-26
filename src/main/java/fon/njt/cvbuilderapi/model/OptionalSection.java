package fon.njt.cvbuilderapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionalSection {
    @Id
    @GeneratedValue()
    private Long optionalTemplateId;
    private String name;
    private boolean repeatable;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "optionalTemplateId")
    private List<OptionalEntity> optionals;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "templateAllSectionsId", name = "templateAllSectionsId",
            foreignKey = @ForeignKey(name = "templateAllSectionsId_fk"))
    private OptionalTemplate optionalTemplate;
}
