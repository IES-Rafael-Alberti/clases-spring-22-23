package es.iesrafaelalberti.clasesspring2223.boot;

import es.iesrafaelalberti.clasesspring2223.models.Cell;
import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import es.iesrafaelalberti.clasesspring2223.repositories.CellRepository;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    PrisonerRepository prisonerRepository;
    @Autowired
    CellRepository cellRepository;

    @Override
    public void run(String... args) {
        Cell cell1 = cellRepository.save(new Cell(3, 30.5f, 25));
        Cell cell2 = cellRepository.save(new Cell(666, 0.5f, 1));
        prisonerRepository.save(new Prisoner("Daniel", 26, 10, cell1));
        prisonerRepository.save(new Prisoner("Jairo", 20, 3, cell1));
        prisonerRepository.save(new Prisoner("Beni", 45, 5, cell2));
    }
}
