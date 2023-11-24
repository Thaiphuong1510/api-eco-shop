package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "amount")
    Float amount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="idCart", referencedColumnName = "id")
    Cart cart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="idOrder", referencedColumnName = "id")
    Order order;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="idProduct", referencedColumnName = "id" )
    Product product;

}
