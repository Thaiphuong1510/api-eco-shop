package com.example.ecoshop.Controller;

import com.example.ecoshop.DTO.OrderDTO;
import com.example.ecoshop.Model.*;
import com.example.ecoshop.Service.CartItemService;
import com.example.ecoshop.Service.CartService;
import com.example.ecoshop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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

    @PostMapping("/update")
    public ResponseEntity<?> updateStatusOrder(@RequestBody Order order){

        OrderDTO responseOrderDTO = orderService.updateStatusOrder(order);
        return ResponseEntity.ok().body(responseOrderDTO);
    }

    @PostMapping("/payment")
    public ResponseEntity<String> payment(@RequestBody List<CartItem> cartItems, @RequestParam(name="idUser") Integer idUser,
                                          @RequestParam(name = "amount") String amount) throws UnsupportedEncodingException {
        OrderDTO responseOrderDTO = orderService.addOder(cartItems,"2", idUser);
        Map<String,String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", PaymentConfig.VERSIONVNPAY);
        vnp_Params.put("vnp_Command", PaymentConfig.COMMAND);
        vnp_Params.put("vnp_TmnCode", PaymentConfig.TMNCODE);
        vnp_Params.put("vnp_Amount", amount+"00");
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", responseOrderDTO.getOrder().getId()+"");
        vnp_Params.put("vnp_OrderInfo", responseOrderDTO.getOrder().getId()+"");
        vnp_Params.put("vnp_OrderType", PaymentConfig.ORDERTYPE);
        vnp_Params.put("vnp_Locale", PaymentConfig.LOCALEDEFAULT);
        vnp_Params.put("vnp_ReturnUrl", "http://localhost:3000/order/payment_return");
        vnp_Params.put("vnp_IpAddr", PaymentConfig.IPDEFAULT);
        Date cld = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);


        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = ConfigPay.hmacSHA512(PaymentConfig.CHECKSUM, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = ConfigPay.vnp_PayUrl + "?" + queryUrl;
        System.out.println(paymentUrl);





//        LocalDateTime now = LocalDateTime.now();




        return new ResponseEntity<>(paymentUrl, HttpStatus.OK);
    }
    @GetMapping("/payment_return")
    public ResponseEntity<?> trans (@RequestParam(value="vnp_ResponseCode") String responseCode,
                                    @RequestParam(value="vnp_OrderInfo") int orderInfo  ){

        OrderDTO responseOrderDTO = orderService.updateStatusPayment(orderInfo,responseCode);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.OK);

    }





}
