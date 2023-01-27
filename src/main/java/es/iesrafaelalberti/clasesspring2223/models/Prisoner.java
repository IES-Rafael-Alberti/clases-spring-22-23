package es.iesrafaelalberti.clasesspring2223.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

@Entity @Getter @Setter
public class Prisoner {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer age;
    @NotNull
    private Integer yearsLeft;

    @ManyToOne
    @JoinColumn()
    private Cell cell;

    // default constructor
    public Prisoner() { }

    public Prisoner(String name, Integer age, @NotNull Integer yearsLeft, Cell cell) {
        this.name = name;
        this.age = age;
        this.yearsLeft = yearsLeft;
        this.cell = cell;
    }
}
