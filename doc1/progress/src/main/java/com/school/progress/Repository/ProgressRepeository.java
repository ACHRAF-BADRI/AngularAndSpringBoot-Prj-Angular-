package com.school.progress.Repository;

import com.school.progress.Entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepeository extends JpaRepository<Progress, Long> {
    Progress findByStatus(String status);
    Progress findProgressById(Long id);
}
