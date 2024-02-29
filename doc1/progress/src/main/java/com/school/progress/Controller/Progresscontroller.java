package com.school.progress.Controller;

import com.school.progress.Service.ProgressService;
import com.school.progress.Entity.Progress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/progress")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class Progresscontroller {
    private final ProgressService progressService;

    @Autowired
    public Progresscontroller(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addProgress(@RequestBody Progress progress){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(progressService.saveProgress(progress));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Progress");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Progress handlePutRequest(@PathVariable Long id, @RequestBody Progress progress) {
        return progressService.updateProgress(id, progress);
    }

    @PatchMapping("update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Progress updateProgress(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return progressService.updateProgressOne(id, updates);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Progress was deleted !!!");
    }

    @GetMapping("find/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Progress findByID(@PathVariable Long id) {
        return progressService.getOneProgress(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Progress>> getAllProgress() {
        try {
            List<Progress> progress = progressService.getAllProgress();
            return ResponseEntity.ok(progress);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
