package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterDTO {
    Category category;
    float bot_price ;
    float top_price;
}
