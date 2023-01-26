package es.iesrafaelalberti.clasesspring2223.boot;

import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    PrisonerRepository prisonerRepository;

    @Override
    public void run(String... args) throws Exception {
        prisonerRepository.save(new Prisoner("Daniel", 26, 10));
        prisonerRepository.save(new Prisoner("Jairo", 20, 3));
        prisonerRepository.save(new Prisoner("Beni", 45, 5));
    }
}
