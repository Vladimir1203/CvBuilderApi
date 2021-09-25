package fon.njt.cvbuilderapi.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionalTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionalTemplateId;
    private String name;
    private boolean repeatable;
    @OneToMany(fetch = FetchType.LAZY)
    private List<OptionalEntity> optionals;
}
