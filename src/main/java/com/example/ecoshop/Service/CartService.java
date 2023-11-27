package com.example.ecoshop.Service;

import com.example.ecoshop.DTO.CartDTO;
import com.example.ecoshop.Model.Cart;
import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Repository.CartItemRepository;
import com.example.ecoshop.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CartItemRepository cartItemRepository;
    public CartDTO saveCart(Cart cart){
        if(cartRepository.save(cart) == null){
            return new CartDTO(null, 1,"system_error") ;
        }

        return new CartDTO(cart, 0,"success") ;
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();

    }
    public CartDTO getCartById(Integer id){
        Cart cart = cartRepository.findById(id).get();
        if(cart == null){
            return new CartDTO(null, 1,"system_error") ;
        }

        return new CartDTO(cart, 0,"success") ;

    }
    public Cart getCartByIdUser(Integer idUser){
        Cart cart = cartRepository.getCartByIdUser(idUser);
        return cart;
    }

   public CartDTO addToCart(CartItem cartItem, Integer idUser){
        Integer id_product = cartItem.getProduct().getId();

        Cart cart = getCartByIdUser(idUser);
        if(cartItem.getQuantity() == 0) {
            return new CartDTO(null, 2,"so luong mat hang > 0");
        }

        CartItem existedCartItem = cartItemRepository.findCartItemByCartAndProduct(id_product, cart.getId());
        if(existedCartItem == null){
            // cart item chua ton tai => luu item cart vao csdl
            cartItem.setCart(cart);
            float amount = cartItem.getProduct().getUnitPrice() * cartItem.getQuantity();
            cartItem.setAmount(amount);

            if(cartItemRepository.save(cartItem) == null){
                return new CartDTO(null,1,"system_error");
            }

        }
        else {
            // cart item da ton tai => cong don quantity bang cach update cartitem
            existedCartItem.setQuantity(existedCartItem.getQuantity() + cartItem.getQuantity());
            existedCartItem.setAmount(existedCartItem.getProduct().getUnitPrice() * existedCartItem.getQuantity());
            if (updateCartItem(existedCartItem) == null) {
                return new CartDTO(null, 1, "system_error");
            }
        }
       List<CartItem> listItem = cart.getCartItemList();
       listItem.add(cartItem);
       cart.setCartItemList(listItem);
        return new CartDTO(cart,0,"success");

   }

    public CartItem updateCartItem(CartItem cartItem){
        CartItem existingcartitem = cartItemRepository.findById(cartItem.getId()).orElse(null);
        existingcartitem.setQuantity(cartItem.getQuantity());
        return cartItemRepository.save(existingcartitem);

    }

    public CartDTO updateCart(Cart cart, CartItem cartItem){
        Cart existingcart = cartRepository.findById(cart.getId()).orElse(null);
        List<CartItem> listItem = cart.getCartItemList();
        listItem.add(cartItem);
        existingcart.setCartItemList(listItem);
        if (cartRepository.save(existingcart) == null){
            return new CartDTO(null,1,"system_error");
        }

        return new CartDTO(cart,0,"success");
    }

}
