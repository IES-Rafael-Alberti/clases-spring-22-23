package es.iesrafaelalberti.clasesspring2223.dto;

import es.iesrafaelalberti.clasesspring2223.models.Cell;
import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CellDTO implements Serializable {
    private Long id;
    private Integer number;
    private Double size;
    private Integer capacity;
    private List<String> prisoners;

    public CellDTO(Cell cell) {
        this.id = cell.getId();
        this.number = cell.getNumber();
        this.capacity = cell.getCapacity();
        this.prisoners = new ArrayList<>();
        for (Prisoner prisoner: cell.getPrisoners()) {
            prisoners.add(prisoner.getName());
        }
    }
}
