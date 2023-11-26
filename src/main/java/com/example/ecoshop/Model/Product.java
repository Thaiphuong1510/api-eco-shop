package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name ="tblProduct")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "nameProduct", columnDefinition = "NVARCHAR(1024)")
    String nameProduct;
    @Column(name = "unitPrice")
    Float unitPrice;
    @Column(name = "description", columnDefinition = "NVARCHAR(1024)")
    String description;
    @Column(name = "images")
    String images;
    @Column(name = "status")
    Boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<CartItem> cartItems;

  //  @JsonIgnore
    @ManyToOne
    @JoinColumn(name ="idCategory", referencedColumnName = "id")
    @JsonIgnoreProperties("products")
    Category category;

}
