package com.school.cours.Repository;

import com.school.cours.Entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepeository extends JpaRepository<Cours, Long> {
    Cours findCoursByName(String name);
    Cours findCoursById(Long id);
}
