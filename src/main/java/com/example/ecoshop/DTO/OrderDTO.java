package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    Order order;
    int error;
    String message;
}
