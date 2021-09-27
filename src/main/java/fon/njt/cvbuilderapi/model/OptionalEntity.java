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
//    @OneToOne(fetch = FetchType.EAGER)
//    private OptionalSection optionalSection;
}
