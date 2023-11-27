package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tblOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

   // @JsonFormat(pattern = "MM/dd/yyyy")
    @Column(name = "thoiGian",  columnDefinition = "DATE DEFAULT CURRENT_DATE")
    LocalDate creatAt;

    @Column(name ="status")
    String status;

    @Column(name ="paymentMethod")
    String paymentMethod;

    @Column(name ="feeShipping")
    Float feeShipping;

//    @Column(name ="totalAmount")
    Float totalAmount;

    @Column(name ="note")
    String note;

    @JsonIgnore
    @JsonIgnoreProperties("order")
    @OneToMany(mappedBy = "order")
    List<CartItem> listOrderItem;

    @JsonIgnoreProperties("orders")
    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    User user;
    @PrePersist
    protected void onCreate() {
        creatAt = LocalDate.now();
    }

}
