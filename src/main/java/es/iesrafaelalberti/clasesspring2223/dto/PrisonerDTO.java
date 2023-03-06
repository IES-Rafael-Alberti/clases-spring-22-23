package es.iesrafaelalberti.clasesspring2223.dto;

import es.iesrafaelalberti.clasesspring2223.models.Cell;
import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrisonerDTO implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    @NotNull
    private Integer yearsLeft;
    private Integer cell_number = 0;

    public PrisonerDTO(Prisoner prisoner) {
        this.id = prisoner.getId();
        this.name = prisoner.getName();
        this.age = prisoner.getAge();
        this.yearsLeft = prisoner.getYearsLeft();
        if(prisoner.getCell() != null)
            this.cell_number = prisoner.getCell().getNumber();
    }
}
