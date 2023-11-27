package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tblCart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
//
//    @Column(name = "total")
    Float total;

    @JsonIgnoreProperties("cart")
    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    User user;

   // @JsonIgnore
//    @JsonIgnoreProperties("cart")
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<CartItem> cartItemList;

    public Cart(Cart cart) {
        this.id = cart.getId();
       // this.total = cart.getTotal();
        this.user = cart.getUser();
    }
}
