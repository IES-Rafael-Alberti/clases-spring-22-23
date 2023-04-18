package es.iesrafaelalberti.clasesspring2223.factories;

import com.github.javafaker.Faker;
import es.iesrafaelalberti.clasesspring2223.models.Cell;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component

public class CellFactory {
    Faker esFaker = new Faker(new Locale("es-ES"));

    public Cell getCell() {
        return new Cell(esFaker.number().numberBetween(100, 399),
                esFaker.number().randomDouble(1, 3, 30),
                esFaker.number().numberBetween(3, 12));
    }

    public List<Cell> get(int number) {
        return IntStream.range(0, number)
                .mapToObj(x -> this.getCell())
                .collect(Collectors.toList());
    }

    public List<Cell> getOldSchool(int number) {
        List<Cell> cells = new ArrayList<>();
        for(int i=0; i<number; i++)
            cells.add(this.getCell());
        return cells;
    }
}
