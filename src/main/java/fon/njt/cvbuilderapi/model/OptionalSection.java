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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OptionalEntity> optionals;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private OptionalTemplate optionalTemplate;
}
