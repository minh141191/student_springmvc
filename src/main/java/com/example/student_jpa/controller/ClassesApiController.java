package com.example.student_jpa.controller;

import com.example.student_jpa.model.Classes;
import com.example.student_jpa.service.classes.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/classes")
public class ClassesApiController {

    @Autowired
    private IClassService classService;

    @GetMapping
    public ResponseEntity<Iterable<Classes>> findAllClasses() {
        List<Classes> classesList = (List<Classes>) classService.findAll();
        if (classesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classes> findClassesById(@PathVariable Long id) {
        Optional<Classes> classesOptional = classService.findById(id);
        if (!classesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(classesOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Classes> createClass(@RequestBody Classes classes) {
        classService.save(classes);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classes> updateClasses(@PathVariable Long id, @RequestBody Classes classes) {
        Optional<Classes> classesOptional = classService.findById(id);
        if (!classesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classes.setId(id);
        classService.save(classes);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Classes> deleteClasses(@PathVariable Long id) {
        Optional<Classes> classesOptional = classService.findById(id);
        if (!classesOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classService.remove(id);
        return new ResponseEntity<>(classesOptional.get(), HttpStatus.NO_CONTENT);
    }
}
