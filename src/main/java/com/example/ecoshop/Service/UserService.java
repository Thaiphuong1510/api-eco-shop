package com.example.ecoshop.Service;

import com.example.ecoshop.Model.User;
import com.example.ecoshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User login(String username, String password){
        User user =userRepository.findUserByUsernameAndPassword(username, password);
        if(user == null){
            return null;
        }
        User userResponse = new User(user);
        System.out.println(userResponse);
        return userResponse;
    }
    public User saveUser(User client){
        return userRepository.save(client);
    }

    public User updateUser(User client){
        User existingUser = userRepository.findById(client.getId()).orElse(null);
        existingUser.setName(client.getName());
        existingUser.setEmail(client.getEmail());
        existingUser.setPhone(client.getPhone());
        existingUser.setAddress(client.getAddress());
        existingUser.setUsername(client.getUsername());
        existingUser.setPassword(client.getPassword());
        existingUser.setImage(client.getImage());
        return userRepository.save(existingUser);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "User removed " + id;
    }


}
