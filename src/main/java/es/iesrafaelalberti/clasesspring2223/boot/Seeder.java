package es.iesrafaelalberti.clasesspring2223.boot;

import es.iesrafaelalberti.clasesspring2223.factories.CellFactory;
import es.iesrafaelalberti.clasesspring2223.factories.PrisonerFactory;
import es.iesrafaelalberti.clasesspring2223.models.Cell;
import es.iesrafaelalberti.clasesspring2223.repositories.CellRepository;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    PrisonerRepository prisonerRepository;
    @Autowired
    CellRepository cellRepository;

    @Autowired
    PrisonerFactory prisonerFactory;
    @Autowired
    CellFactory cellFactory;

    @Override
    public void run(String... args) {
        List<Cell> cells = cellFactory.get(3);
        cellRepository.saveAll(cells);
        prisonerRepository.saveAll(prisonerFactory.get(7, cells));
    }
}
