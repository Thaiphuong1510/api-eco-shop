package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Model.Category;
import com.example.ecoshop.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ProductDTO {
    Product product;
    int error;
    String message;
}
