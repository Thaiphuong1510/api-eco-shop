package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    Category category;
    int error;
    String message;
}
