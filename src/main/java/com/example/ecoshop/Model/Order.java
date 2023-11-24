package com.example.ecoshop.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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

    @JsonFormat(pattern = "MM/dd/yyyy")
    @Column(name = "thoiGian", columnDefinition = "DATE")
    Date creatAt;

    @Column(name ="status")
    String status;

    @Column(name ="paymentMethod")
    String paymentMethod;

    @Column(name ="feeShipping")
    Float feeShipping;

    @Column(name ="totalAmount")
    Float totalAmount;

    @Column(name ="note")
    String note;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<CartItem> listOrderItem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    User user;

    public Order(Order order){
        this.id = id;
        this.creatAt = creatAt;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.feeShipping = feeShipping;
        this.totalAmount = totalAmount;
        this.note = note;
    }
}
