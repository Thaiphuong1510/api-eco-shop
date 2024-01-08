package com.example.ecoshop.Service;

import com.example.ecoshop.DTO.CartItemDTO;
import com.example.ecoshop.DTO.OrderDTO;
import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Model.Order;
import com.example.ecoshop.Model.Product;
import com.example.ecoshop.Model.User;
import com.example.ecoshop.Repository.CartItemRepository;
import com.example.ecoshop.Repository.OrderRepository;
import com.example.ecoshop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    CartItemRepository cartItemRepository;
    public OrderDTO addOder(List<CartItem> cartItemList,String paymentMethod, Integer idUser){
        Order order = new Order();
        order.setStatus("Chờ lấy hàng");
        order.setPaymentMethod(paymentMethod);
        order.setFeeShipping(0.0f);
        order.setTotalAmount(calculateTotalAmount(cartItemList));
        order.setNote("");

        User user = userRepository.findById(idUser).get();
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        if(savedOrder == null){
            return new OrderDTO(null,1,"system_error");
        }
        List<CartItem> list= new ArrayList<>();
        //CartItem cartItem = new CartItem();
        for(CartItem i : cartItemList){
            CartItem cartItem = updateCartItem(i,order);
            list.add(cartItem);
        }
        savedOrder.setListOrderItem(list);

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

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersByUser(int idUser){
        User user = userRepository.findById(idUser).get();
        List<Order> orderList = orderRepository.findAllByUser(user);
        if(orderList == null) return null;
        return orderList;
    }

    public OrderDTO getOrderById(int id){
        Order order = orderRepository.findById(id).get();
        if(order == null ){
            return new OrderDTO(null,1,"system_error");
        }
         return new OrderDTO(order,0,"success");
    }

    public CartItem updateCartItem(CartItem cartItem, Order order){
        CartItem existingcartitem = cartItemRepository.findById(cartItem.getId()).orElse(null);
        existingcartitem.setCart(null);
        existingcartitem.setOrder(order);
        existingcartitem.setQuantity(cartItem.getQuantity());
        float amount = cartItem.getUnitPrice() * existingcartitem.getQuantity();
        existingcartitem.setAmount(amount);
        return cartItemRepository.save(existingcartitem);

    }
    public OrderDTO updateStatusOrder(Order order){
        Order existingOrder = orderRepository.findById(order.getId()).orElse(null);
        existingOrder.setStatus(order.getStatus());
        Order order1 = orderRepository.save(existingOrder);
        if(order == null) return new OrderDTO(null, 1, "system_error");
        return new OrderDTO(order1, 0, "success");

    }


    public OrderDTO updateStatusPayment(int orderId, String responseCode) {
        Order existingOrder = orderRepository.findById(orderId).get();
        if ("00".equals(responseCode)) {
            existingOrder.setNote("Thanh toán thành công");
        } else {
            existingOrder.setNote("Thanh toán thất bại");
            existingOrder.setStatus("Huỷ đơn hàng");
        }
        orderRepository.save(existingOrder);
        return new OrderDTO(existingOrder, 0, "success");
    }
}
