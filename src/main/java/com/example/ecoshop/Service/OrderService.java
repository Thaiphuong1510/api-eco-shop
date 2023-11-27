package com.example.ecoshop.Service;

import com.example.ecoshop.DTO.OrderDTO;
import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Model.Order;
import com.example.ecoshop.Model.User;
import com.example.ecoshop.Repository.OrderRepository;
import com.example.ecoshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    public OrderDTO addOder(List<CartItem> cartItemList,String paymentMethod, Integer idUser){


        Order order = new Order();
        order.setStatus("Pending");
        order.setPaymentMethod(paymentMethod);
        order.setFeeShipping(0.0f);
        order.setTotalAmount(calculateTotalAmount(cartItemList));
        order.setNote("huhu");
        order.setListOrderItem(cartItemList);

        User user = userRepository.findById(idUser).get();
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        if(savedOrder == null){
            return new OrderDTO(null,1,"system_error");
        }
        return new OrderDTO(savedOrder,0,"Order created successfully");
    }
    private Float calculateTotalAmount(List<CartItem> cartItemList) {
        Float totalAmount = 0.0f;

        for (CartItem cartItem : cartItemList) {
            totalAmount += cartItem.getAmountTT();
        }

        return totalAmount;
    }
    public List<Order> findByOrderDateTimeBetween(LocalDate startDate, LocalDate endDate){
        return  orderRepository.findByCreatAtBetween(startDate,endDate);
    }



}
