package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tblUser")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name ="username")
    String username;

    @Column(name="password")
    String password;

    @Column(name = "name", columnDefinition = "NVARCHAR(255)")
    String name;

    @Column(name = "image")
    String image;

    @Column(name = "phone")
    String phone;

    @Column(name = "email", columnDefinition = "NVARCHAR(255)")
    String email;

    @Column(name = "address", columnDefinition = "NVARCHAR(255)")
    String address;


    @OneToMany(mappedBy = "user")
    List<Order> orders;

    @JsonIgnore
    @OneToOne(mappedBy = "user")

    Cart cart;

    @JsonIgnoreProperties("user")
    @OneToOne(mappedBy = "user")
    Staff staff;

    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.image = user.getImage();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.address = user.getAddress();
    }
}
