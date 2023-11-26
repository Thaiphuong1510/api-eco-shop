package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.Cart;
import com.example.ecoshop.Model.Order;
import com.example.ecoshop.Model.Staff;
import com.example.ecoshop.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    User user;
    int error;
    String message;
}
