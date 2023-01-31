package es.iesrafaelalberti.clasesspring2223.factories;

import com.github.javafaker.Faker;
import es.iesrafaelalberti.clasesspring2223.models.Cell;
import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class PrisonerFactory {
    Faker esFaker = new Faker(new Locale("es-ES"));
    Random rand = new Random();

    public List<Prisoner> get(int number, List<Cell> cells) {
        return IntStream.range(0, number)
                .mapToObj(x -> new Prisoner(esFaker.name().firstName(),
                                        esFaker.number().numberBetween(18, 65),
                                        esFaker.number().numberBetween(5, 65),
                                        cells.get(rand.nextInt(cells.size()))))
                .collect(Collectors.toList());
    }

    public List<Prisoner> getOldSchool(int number, List<Cell> cells) {
        List<Prisoner> prisoners = new ArrayList<>();
        for(int i=0; i<number; i++)
            prisoners.add(new Prisoner(esFaker.name().firstName(),
                    esFaker.number().numberBetween(18, 65),
                    esFaker.number().numberBetween(5, 65),
                    cells.get(rand.nextInt(cells.size()))));

        return prisoners;
    }
}
