package fon.njt.cvbuilderapi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionalEntity {
    @Id
    @GeneratedValue()
    private Long optionalEntityId;
    private String optionalType;
    private String optionalColumn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "optionalTemplateId", name = "optionalTemplateId",
            foreignKey = @ForeignKey(name = "optionalTemplateId_fk"))
    private OptionalSection optionalSection;

}
