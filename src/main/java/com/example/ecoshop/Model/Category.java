package com.example.ecoshop.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tblCategory")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "nameCategory", columnDefinition = "NVARCHAR(1024)")
    String nameCategory;

    @Column(name = "description", columnDefinition = "NVARCHAR(1024)")
    String description;

    @Column(name = "status")
    Boolean status;

   // @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Product> products;


}
