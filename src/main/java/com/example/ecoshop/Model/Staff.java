package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tblStaff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "role", columnDefinition = "NVARCHAR(255)")
    String role;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    User user;



}
