package com.example.ecoshop.Service;

import com.example.ecoshop.DTO.UserDTO;
import com.example.ecoshop.Model.Cart;
import com.example.ecoshop.Model.User;
import com.example.ecoshop.Repository.CartRepository;
import com.example.ecoshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    public boolean isUsernameExists(String username){
        User userExists = userRepository.findUserByUsername(username);
        if(userExists == null) return false;
        return true;
    }
    public boolean isUsernameExistsInUpdate(String username, Integer idUser){
        User userExists = userRepository.findUserByUsername(username);
       // if(userExists == null) return false;

        return userExists != null && !userExists.getId().equals(idUser);
    }
    public UserDTO login(String username, String password){
        User user =userRepository.findUserByUsernameAndPassword(username, password);
        if(user == null){
            return new UserDTO(null, 1,"system_error");
        }
        return new UserDTO(user, 0, "success");
    }
    public UserDTO saveUser(User user){
        if( isUsernameExists(user.getUsername())){
            return new UserDTO(null, 1,"ten tk bi trung") ;
        }
        Cart newCart = new Cart();
        System.out.println(newCart);
        user = userRepository.save(user);
        newCart.setUser(user);
        cartRepository.save(newCart);
        return new UserDTO(user, 0, "success");
    }

    public UserDTO updateUser(User client){
        User existingUser = userRepository.findById(client.getId()).orElse(null);
        existingUser.setName(client.getName());
        existingUser.setEmail(client.getEmail());
        existingUser.setPhone(client.getPhone());
        existingUser.setAddress(client.getAddress());
        existingUser.setUsername(client.getUsername());
        existingUser.setPassword(client.getPassword());
        existingUser.setImage(client.getImage());
        if( isUsernameExistsInUpdate(existingUser.getUsername(), existingUser.getId()) ){
            return new UserDTO(null, 1,"ten tk bi trung") ;
        }
        userRepository.save(existingUser);
        return new UserDTO(existingUser, 0, "success");
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User getUserById( int id){
        return userRepository.findById(id).get();
    }

    public String deleteUser(int id){
        userRepository.deleteById(id);
        return "User removed " + id;
    }


}
