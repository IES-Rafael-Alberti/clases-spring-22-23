package es.iesrafaelalberti.clasesspring2223.controllers;

import es.iesrafaelalberti.clasesspring2223.dto.CellCreateDTO;
import es.iesrafaelalberti.clasesspring2223.dto.CellDTO;
import es.iesrafaelalberti.clasesspring2223.models.Cell;
import es.iesrafaelalberti.clasesspring2223.repositories.CellRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CellController {
    @Autowired
    CellRepository cellRepository;

    @GetMapping("/cells/")
    public ResponseEntity<Object> index() {
        List<CellDTO> resultado = new ArrayList<>();
        for (Cell cell:cellRepository.findAll()) {
            resultado.add(new CellDTO(cell));
        }
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @GetMapping("/cells/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        Cell cell = cellRepository.findById(id)
                                  .orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return new ResponseEntity<>(new CellDTO(cell), HttpStatus.OK);
    }

    @PostMapping("/cells/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> create(@RequestBody CellCreateDTO cellCreateDTO) {
        Cell cell = cellRepository.save(new Cell(cellCreateDTO));
        return new ResponseEntity<>(cell, HttpStatus.OK);
    }

    @DeleteMapping("/cells/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Cell> cell = cellRepository.findById(id);
        cell.ifPresent(value -> cellRepository.delete(value));
        return new ResponseEntity<>(cell.isPresent(), cell.isPresent()? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/cells/{id}/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody Cell cell) {
        Optional<Cell> oldCell = cellRepository.findById(id);
        if(oldCell.isPresent()) {
            cell.setId(id);
            cellRepository.save(cell);
            return new ResponseEntity<>(cell, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
