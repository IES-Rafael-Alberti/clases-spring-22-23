package es.iesrafaelalberti.clasesspring2223.services;

import es.iesrafaelalberti.clasesspring2223.dto.PrisonerCreateDTO;
import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PrisonerService {
    @Autowired
    PrisonerRepository prisonerRepository;
    public Prisoner prisonerCreate(PrisonerCreateDTO newPrisoner) {
        return prisonerRepository.save(new Prisoner(newPrisoner));
    }
}
