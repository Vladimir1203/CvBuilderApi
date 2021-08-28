package fon.njt.cvbuilderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    @Id
    private Long fieldId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "sectionId", name = "sectionId")
    private Section section;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "fieldTypeID", name = "fieldTypeID")
    private FieldType fieldType;
    private String value;
}
