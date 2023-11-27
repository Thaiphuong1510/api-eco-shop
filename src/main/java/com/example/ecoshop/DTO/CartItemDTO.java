package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    CartItem cartItem;
    int error;
    String message;



//    public CartItemDTO(CartItem cartItem, int error, String message) {
//        this.cartItem = cartItem;
//        this.amount = getAmount();
//        this.error = error;
//        this.message = message;
//    }
}
