package com.example.ecoshop.Controller;

import com.example.ecoshop.DTO.OrderDTO;
import com.example.ecoshop.Model.CartItem;
import com.example.ecoshop.Model.Order;
import com.example.ecoshop.Model.Product;
import com.example.ecoshop.Service.CartItemService;
import com.example.ecoshop.Service.CartService;
import com.example.ecoshop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;

    @Autowired
    OrderService orderService;

//, @RequestParam Integer idUser, @RequestParam String paymentMethod @RequestBody List<CartItem> cartItems
    @PostMapping("/saveOrder")
    public ResponseEntity<?> addToCart(@RequestBody List<CartItem> cartItems, @RequestParam(name="idUser") Integer idUser, @RequestParam(name="paymentMethod") String paymentMethod){

        OrderDTO responseOrderDTO = orderService.addOder(cartItems,paymentMethod, idUser);
        return ResponseEntity.ok().body(responseOrderDTO);
    }
    @GetMapping("/findByDate/{startDate}/{endDate}")
    public ResponseEntity<List<Order>> findByDate(@PathVariable(name="startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @PathVariable(name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
                               ) {

        return ResponseEntity.ok().body(orderService.findByOrderDateTimeBetween(startDate,endDate));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id){
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }
    @GetMapping("/ordersByUser/{idUser}")
    public ResponseEntity<List<Order>> getOrderByIdUser(@PathVariable int idUser){
        return ResponseEntity.ok().body(orderService.getAllOrdersByUser(idUser));
    }




}
