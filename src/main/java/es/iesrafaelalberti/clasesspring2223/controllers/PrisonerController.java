package es.iesrafaelalberti.clasesspring2223.controllers;

import es.iesrafaelalberti.clasesspring2223.dto.PrisonerCreateDTO;
import es.iesrafaelalberti.clasesspring2223.dto.PrisonerDTO;
import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import es.iesrafaelalberti.clasesspring2223.services.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
public class PrisonerController {
    @Autowired
    PrisonerRepository prisonerRepository;

    @Autowired
    PrisonerService prisonerService;

    @GetMapping("/prisoners/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(
                StreamSupport.stream(prisonerRepository.findAll().spliterator(), false)
                        .map(PrisonerDTO::new),
                HttpStatus.OK);
    }

    @GetMapping("/prisoners/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        Optional<Prisoner> prisoner = prisonerRepository.findById(id);
        return new ResponseEntity<>(prisoner.isPresent()? new PrisonerDTO(prisoner.get()) : false,
                                    prisoner.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/prisoners/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> create(@RequestBody PrisonerCreateDTO prisoner) {
        return new ResponseEntity<>(
                new PrisonerDTO(prisonerService.prisonerCreate(prisoner)),
                HttpStatus.OK);
    }

    @DeleteMapping("/prisoners/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Prisoner> prisoner = prisonerRepository.findById(id);
        prisoner.ifPresent(value -> prisonerRepository.delete(value));
        return new ResponseEntity<>(prisoner.isPresent(), prisoner.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/prisoners/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Prisoner prisoner) {
        Optional<Prisoner> oldPrisoner = prisonerRepository.findById(id);
        if(oldPrisoner.isPresent()) {
            prisoner.setId(id);
            prisonerRepository.save(prisoner);
            return new ResponseEntity<>(prisoner, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
