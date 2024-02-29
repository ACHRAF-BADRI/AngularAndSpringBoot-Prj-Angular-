package com.school.user.Service;

import com.school.user.Repository.userRepeository;
import com.school.user.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final userRepeository userRepeository;

    @Autowired
    public UserService(userRepeository userRepeository){
        this.userRepeository = userRepeository;
    }
    public User saveUser(User user) {
        try { //The admin will choose the user role (Admin, Instructor or Student)
            return userRepeository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving user: " + e.getMessage());
        }
    }
    public User saveStudent(User user) {
        try {
            user.setRole("STUDENT"); //THE ROLE IS STUDENT BY DEFAULT WHEN HE REGISTERS IN THE WEBAPP
            return userRepeository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving student: " + e.getMessage());
        }
    }
    public User updateUser(long id,User user) {
        Optional<User> optionalUser = userRepeository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            existingUser.setFull_name(user.getFull_name());

            return userRepeository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
    public User updateUserOne(Long id, Map<String, Object> updates) {
        User currentUser = userRepeository.findById(id).orElseThrow(RuntimeException::new);

        if (updates.containsKey("username"))
            currentUser.setUsername(updates.get("username").toString());
        if (updates.containsKey("email"))
            currentUser.setPassword(updates.get("email").toString());
        if (updates.containsKey("password"))
            currentUser.setPassword(updates.get("password").toString());
        if (updates.containsKey("role"))
            currentUser.setRole(updates.get("role").toString());
        if (updates.containsKey("full_name"))
            currentUser.setFull_name(updates.get("full_name").toString());

        return userRepeository.save(currentUser);
    }

    public User getOneUser(Long id) {
        return userRepeository.findUserById(id);
    }

    public List<User> getUsersByRole(String role) {
        return userRepeository.findAllByRole(role);
    }

    public void deleteUser(Long id) {
        userRepeository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepeository.findAll();
    }

    public Boolean checkUser(String username, String password)
    {
        User user1 = userRepeository.findByUsername(username);

        if (user1 != null && user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
                return  Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public String checkRole(String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepeository.findByUsername(username));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getRole();
        } else {
            return "UNKNOWN";
        }
    }

    public Long getIdByUN(String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepeository.findByUsername(username));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getId();
        } else {
            return null;
        }
    }
    public User getUserByUsername(String username) {
        return userRepeository.findByUsername(username);
    }
    public User getUserByPassword(String password) {
        return userRepeository.findUserByPassword(password);
    }

}
