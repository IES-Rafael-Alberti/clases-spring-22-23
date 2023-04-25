package es.iesrafaelalberti.clasesspring2223.models;

import es.iesrafaelalberti.clasesspring2223.dto.PrisonerCreateDTO;
import es.iesrafaelalberti.clasesspring2223.dto.PrisonerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

@Entity @Getter @Setter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "name", columnList = "name"),
        @Index(name = "age", columnList = "age")
})
public class Prisoner {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    @NotNull
    private Integer yearsLeft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Cell cell;

    public Prisoner(String name, Integer age, @NotNull Integer yearsLeft, Cell cell) {
        this.name = name;
        this.age = age;
        this.yearsLeft = yearsLeft;
        this.cell = cell;
    }

    public Prisoner(PrisonerCreateDTO newPrisoner) {
        this.name = newPrisoner.getName();
        this.age = newPrisoner.getAge();
        this.yearsLeft = newPrisoner.getYearsLeft();
    }
}
