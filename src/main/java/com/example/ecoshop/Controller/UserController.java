package com.example.ecoshop.Controller;

import com.example.ecoshop.DTO.UserDTO;
import com.example.ecoshop.Model.User;
import com.example.ecoshop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        UserDTO responseUser = userService.login(user.getUsername(), user.getPassword());
        return ResponseEntity.ok().body(responseUser);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        UserDTO responseUserDTO = userService.saveUser(user);
        return ResponseEntity.ok().body(responseUserDTO);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User responseUser = userService.updateUser(user);
        return ResponseEntity.ok().body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping("/users/id")
    public ResponseEntity<User> getAllUsers(@PathVariable int id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }


}
