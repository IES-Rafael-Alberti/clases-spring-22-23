package es.iesrafaelalberti.clasesspring2223.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    // default constructor
    public Prisoner() { }

    public Prisoner(String name, Integer age, Integer yearsLeft) {
        this.name = name;
        this.age = age;
        this.yearsLeft = yearsLeft;
    }
}
