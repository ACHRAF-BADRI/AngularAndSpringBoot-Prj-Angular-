package com.school.cours.Service;

import com.school.cours.Repository.CoursRepeository;
import com.school.cours.Entity.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService {

    private final CoursRepeository coursRepeository;

    @Autowired
    public CoursService(CoursRepeository coursRepeository){
        this.coursRepeository = coursRepeository;
    }

    public Cours saveCours(Cours cours) {
        try {
            return coursRepeository.save(cours);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving prof: " + e.getMessage());
        }
    }

    public Cours updateCours(long id, Cours cours) {
        Optional<Cours> optionalCours = coursRepeository.findById(id);
        if (optionalCours.isPresent()) {
            Cours existingCours = optionalCours.get();
            existingCours.setName(cours.getName());
            existingCours.setProf(cours.getProf());

            return coursRepeository.save(existingCours);
        } else {
            throw new RuntimeException("Cours not found with ID: " + id);
        }
    }
    public Cours updateCoursOne(Long id, Map<String, Object> updates) {
        Cours currentCours = coursRepeository.findById(id).orElseThrow(RuntimeException::new);

        if (updates.containsKey("name"))
            currentCours.setName(updates.get("name").toString());
        if (updates.containsKey("prof"))
            currentCours.setProf(updates.get("prof").toString());

        return coursRepeository.save(currentCours);
    }

    public Cours getOneCours(Long id) {
        return coursRepeository.findCoursById(id);
    }

    public void deleteCours(Long id) {
        coursRepeository.deleteById(id);
    }

    public List<Cours> getAllCours() {
        return coursRepeository.findAll();
    }
    public Cours getCoursByCoursname(String name) {
        return coursRepeository.findCoursByName(name);
    }

}
