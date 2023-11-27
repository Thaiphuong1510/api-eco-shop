package com.example.ecoshop.Repository;

import com.example.ecoshop.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query(value = "SELECT * FROM ecoshop.tbl_cart_item WHERE id_product=:product AND id_cart=:cart", nativeQuery = true)
    CartItem findCartItemByCartAndProduct(@Param("product") Integer id_product, @Param("cart") Integer id_cart);



}
