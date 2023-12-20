package com.example.ecoshop.Service;

import com.example.ecoshop.DTO.CartItemDTO;
import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    public List<CartItem> getAllItems(){
        return cartItemRepository.findAll();
    }
    public CartItemDTO saveItemCart(CartItem cartItem){
        if(cartItemRepository.save(cartItem) == null){
            return new CartItemDTO(null, 1,"system_error") ;
        }
        //float amount = cartItem.getProduct().getUnitPrice() * cartItem.getQuantity();

        return new CartItemDTO(cartItem,0,"success") ;
    }
    public CartItemDTO updateCartItem(CartItem cartItem){
        CartItem existingcartitem = cartItemRepository.findById(cartItem.getId()).orElse(null);
       // existingcartitem.setOrder();
        existingcartitem.setQuantity(cartItem.getQuantity());
        if(cartItemRepository.save(existingcartitem) == null){
            return new CartItemDTO(null, 1, "system_error");
        }
        return new CartItemDTO(existingcartitem, 0 , "success");

    }
    public String deletecartItem(int id){
        cartItemRepository.deleteById(id);
        return "CartItem removed " + id;
    }
    public CartItem findId(Integer id){
        return cartItemRepository.findById(id).get();

    }
}
