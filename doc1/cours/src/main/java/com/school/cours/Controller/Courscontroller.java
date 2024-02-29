package com.school.cours.Controller;

import com.school.cours.Service.CoursService;
import com.school.cours.Entity.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cours")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class Courscontroller {
    private final CoursService coursService;

    @Autowired
    public Courscontroller(CoursService coursService) {
        this.coursService = coursService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCours(@RequestBody Cours cours){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(coursService.saveCours(cours));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating cours");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cours handlePutRequest(@PathVariable Long id, @RequestBody Cours cours) {
        return coursService.updateCours(id, cours);
    }

    @PatchMapping("update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cours updateCours(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return coursService.updateCoursOne(id, updates);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteCours(@PathVariable Long id) {
        coursService.deleteCours(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cours was deleted !!!");
    }

    @GetMapping("find/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cours findByID(@PathVariable Long id) {
        return coursService.getOneCours(id);
    }

    @GetMapping("onecours/find/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cours findByName(@PathVariable String name) {
        return coursService.getCoursByCoursname(name);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cours>> getAllCours() {
        try {
            List<Cours> cours = coursService.getAllCours();
            return ResponseEntity.ok(cours);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
