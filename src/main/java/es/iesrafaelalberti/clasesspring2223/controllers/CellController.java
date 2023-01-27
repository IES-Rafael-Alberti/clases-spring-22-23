package es.iesrafaelalberti.clasesspring2223.controllers;

import es.iesrafaelalberti.clasesspring2223.models.Cell;
import es.iesrafaelalberti.clasesspring2223.repositories.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CellController {
    @Autowired
    CellRepository cellRepository;

    @GetMapping("/cells/")
    public ResponseEntity<Object> index() {
        return new ResponseEntity<>(cellRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/cells/{id}/")
    public ResponseEntity<Object> show(@PathVariable("id") Long id) {
        return new ResponseEntity<>(cellRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/cells/create")
    public ResponseEntity<Object> create(@RequestBody Cell cell) {
        cellRepository.save(cell);
        return new ResponseEntity<>(cell, HttpStatus.OK);
    }

    @DeleteMapping("/cells/{id}/")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        Optional<Cell> cell = cellRepository.findById(id);
        cell.ifPresent(value -> cellRepository.delete(value));
        return new ResponseEntity<>(cell.isPresent(), HttpStatus.OK);
    }

    @PutMapping("/cells/{id}/")
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
