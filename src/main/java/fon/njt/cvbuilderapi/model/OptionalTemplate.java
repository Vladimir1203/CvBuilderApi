package fon.njt.cvbuilderapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OptionalTemplate {
    @Id
    @GeneratedValue()
    private Long templateAllSectionsId;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    private List<OptionalSection> optionalSections;

}
