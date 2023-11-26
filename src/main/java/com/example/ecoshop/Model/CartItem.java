package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tblCartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "unitPrice")
    Float unitPrice;

    @Column(name = "quantity")
    Integer quantity;

//    @Column(name = "amount")
//    @Formula("unitPrice * quantity")
//    @Transient
    Float amount;
   // @JsonIgnore
   @JsonIgnoreProperties("cartItemList")
    @ManyToOne
    @JoinColumn(name ="idCart", referencedColumnName = "id")
    Cart cart;


    @ManyToOne

    @JoinColumn(name ="idOrder", referencedColumnName = "id")
    Order order;


    @ManyToOne
    @JoinColumn(name ="idProduct", referencedColumnName = "id" )
    @JsonIgnoreProperties("cartItems")
    Product product;

//    public double getAmount() {
//        return this.unitPrice * this.quantity;
//    }
}
