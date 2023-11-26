package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.Cart;
import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    Cart cart;
    int error;
    String message;

}
