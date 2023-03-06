package es.iesrafaelalberti.clasesspring2223.dto;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PrisonerCreateDTO implements Serializable {
    private String name;
    private Integer age;
    @NotNull
    private Integer yearsLeft;
}
