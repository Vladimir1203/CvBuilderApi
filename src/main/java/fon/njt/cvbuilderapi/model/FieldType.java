package fon.njt.cvbuilderapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FieldType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldTypeId;
    private String type;
}
