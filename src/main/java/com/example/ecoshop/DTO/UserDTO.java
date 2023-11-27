package com.example.ecoshop.DTO;

import com.example.ecoshop.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    User user;
    int error;
    String message;
}
