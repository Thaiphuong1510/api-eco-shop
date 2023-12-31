package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    Cart cart;
    int error;
    String message;

}
