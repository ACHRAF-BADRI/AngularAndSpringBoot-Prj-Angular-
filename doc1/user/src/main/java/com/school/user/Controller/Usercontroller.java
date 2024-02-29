package com.school.user.Controller;

import com.school.user.Service.UserService;
import com.school.user.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class Usercontroller {
    private final UserService userService;

    @Autowired
    public Usercontroller (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user");
        }
    }

    @PostMapping("/student/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addStudent(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveStudent(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating student");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User handlePutRequest(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PatchMapping("update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return userService.updateUserOne(id, updates);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User was deleted !!!");
    }

    @GetMapping("find/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User findByID(@PathVariable Long id) {
        return userService.getOneUser(id);
    }

    @GetMapping("find/role/{role}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<User> findByRole(@PathVariable String role) {
        return userService.getUsersByRole(role);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/auth/{username}/{password}")
    public Boolean authenticate(@PathVariable String username,@PathVariable  String password) {
        if(userService.checkUser(username, password) == Boolean.TRUE) {
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
    }

    @GetMapping("/check_role/{username}")
    public ResponseEntity<Map<String, String>> checkRole(@PathVariable String username) {
        String role = userService.checkRole(username);

        Map<String, String> response = new HashMap<>();
        response.put("role", role);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getid/{username}")
    public ResponseEntity<Map<String, Long>> getIdByUsername(@PathVariable String username) {
        Long id = userService.getIdByUN(username);

        Map<String, Long> response = new HashMap<>();
        response.put("id", id);

        return ResponseEntity.ok(response);
    }

}
