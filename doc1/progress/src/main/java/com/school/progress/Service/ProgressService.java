package com.school.progress.Service;

import com.school.progress.Repository.ProgressRepeository;
import com.school.progress.Entity.Progress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepeository progressRepeository;

    @Autowired
    public ProgressService(ProgressRepeository progressRepeository){
        this.progressRepeository = progressRepeository;
    }

    public Progress saveProgress(Progress progress) {
        try {
            return progressRepeository.save(progress);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving progress: " + e.getMessage());
        }
    }

    public Progress updateProgress(long id, Progress progress) {
        Optional<Progress> optionalprogress = progressRepeository.findById(id);
        if (optionalprogress.isPresent()) {
            Progress existingprogress = optionalprogress.get();
            existingprogress.setStatus(progress.getStatus());
            existingprogress.setModule(progress.getModule());

            return progressRepeository.save(existingprogress);
        } else {
            throw new RuntimeException("Progress not found with ID: " + id);
        }
    }
    public Progress updateProgressOne(Long id, Map<String, Object> updates) {
        Progress currentProgress = progressRepeository.findById(id).orElseThrow(RuntimeException::new);

        if (updates.containsKey("status"))
            currentProgress.setStatus(updates.get("status").toString());
        if (updates.containsKey("module"))
            currentProgress.setModule(updates.get("module").toString());

        return progressRepeository.save(currentProgress);
    }

    public Progress getOneProgress(Long id) {
        return progressRepeository.findProgressById(id);
    }

    public void deleteProgress(Long id) {
        progressRepeository.deleteById(id);
    }

    public List<Progress> getAllProgress() {
        return progressRepeository.findAll();
    }
    public Progress getCoursByProgressname(String status) {
        return progressRepeository.findByStatus(status);
    }

}
