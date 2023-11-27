package com.example.ecoshop.Controller;

import com.example.ecoshop.DTO.CartDTO;
import com.example.ecoshop.Model.Cart;
import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Service.CartItemService;
import com.example.ecoshop.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;
    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllcarts(){
        Cart cart = cartService.getAllCarts().get(0);
        System.out.println(cart.getUser().getName());
        return ResponseEntity.ok().body(cartService.getAllCarts());
    }

    @GetMapping("/itemCarts")
    public ResponseEntity<List<CartItem>> getAllItemCarts(){
//        CartItem cartItem = cartItemService.getAllItems().get(0);
//        System.out.println(cartItem);
        return ResponseEntity.ok().body(cartItemService.getAllItems());
    }
    @PostMapping("/add")
    public ResponseEntity<?> addNewCart(@RequestBody Cart cart){
        CartDTO responseCart = cartService.saveCart(cart);
        return ResponseEntity.ok().body(responseCart);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartItem cartItem, @RequestParam Integer idUser){
        CartDTO responseCartDTO = cartService.addToCart(cartItem, idUser);
        return ResponseEntity.ok().body(responseCartDTO);
    }
}
