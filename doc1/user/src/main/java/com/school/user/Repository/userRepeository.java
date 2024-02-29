package com.school.user.Repository;

import com.school.user.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepeository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findUserByPassword(String password);
    User findUserById(Long id);
    User findUserByRole(String role);
    List<User> findAllByRole(String role);
}
