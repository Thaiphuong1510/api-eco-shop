package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.Category;
import com.example.ecoshop.Model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    Category category;
    int error;
    String message;
}
