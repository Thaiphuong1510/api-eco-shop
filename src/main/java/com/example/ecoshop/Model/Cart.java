package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @Column(name = "total")
    Float total;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    User user;

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItemList;
}
