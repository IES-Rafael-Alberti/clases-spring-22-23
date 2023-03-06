package es.iesrafaelalberti.clasesspring2223.controllers;

import es.iesrafaelalberti.clasesspring2223.dto.PrisonerCreateDTO;
import es.iesrafaelalberti.clasesspring2223.dto.PrisonerDTO;
import es.iesrafaelalberti.clasesspring2223.models.Prisoner;
import es.iesrafaelalberti.clasesspring2223.repositories.PrisonerRepository;
import es.iesrafaelalberti.clasesspring2223.services.PrisonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PrisonerController {
    @Autowired
    PrisonerRepository prisonerRepository;

    @Autowired
    PrisonerService prisonerService;

    @GetMapping("/prisoners/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(
                prisonerRepository.findAll(),
                HttpStatus.OK);
    }

    @GetMapping("/prisoners/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new PrisonerDTO(prisonerRepository.findById(id).get()),
                HttpStatus.OK);
    }

    @PostMapping("/prisoners/create")
    public ResponseEntity<Object> create(@RequestBody PrisonerCreateDTO prisoner) {
        return new ResponseEntity<>(
                new PrisonerDTO(prisonerService.prisonerCreate(prisoner)),
                HttpStatus.OK);
    }

    @DeleteMapping("/prisoners/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Prisoner> prisoner = prisonerRepository.findById(id);
        prisoner.ifPresent(value -> prisonerRepository.delete(value));
        return new ResponseEntity<>(prisoner.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/prisoners/{id}/")
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
