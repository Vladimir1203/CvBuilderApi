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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionalEntityId;
    private String optionalType;
    private String optionalColumn;
    @ManyToOne(fetch = FetchType.EAGER)
    private OptionalTemplate optionalTemplate;

}
