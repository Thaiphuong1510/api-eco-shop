package com.example.ecoshop.Repository;

import com.example.ecoshop.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "SELECT * FROM ecoshop.tbl_cart WHERE `tbl_cart.id_user` = :idUser", nativeQuery = true)
    Cart getCartByIdUser(Integer idUser);
}
