package com.example.ecoshop.Controller;

import com.example.ecoshop.DTO.FilterDTO;
import com.example.ecoshop.Model.Product;
import com.example.ecoshop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
    @GetMapping("/productsByActive")
    public ResponseEntity<List<Product>> getAllProductsByActive(){
        return ResponseEntity.ok().body(productService.getProductsByActive());
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        //System.out.println(productService.getProductById(id));
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @GetMapping("/getByName/{nameProduct}")
    public ResponseEntity<List<Product>> findProductBySearchName(@PathVariable String nameProduct){
        return ResponseEntity.ok().body(productService.findBySearchNameProduct(nameProduct));
    }

    @GetMapping("/getAllByFilter")
    public ResponseEntity<List<Product>> getAllByFilter(@RequestBody FilterDTO filterDto){
        return ResponseEntity.ok().body(productService.getProductByFilter(filterDto));
    }
    @GetMapping("/getAllByPriceAsc")
    public ResponseEntity<List<Product>> findAllByPriceAsc(){
        return ResponseEntity.ok().body(productService.findAllByPriceAsc());
    }
    @GetMapping("/getAllByPriceDesc")
    public ResponseEntity<List<Product>> findAllByPriceDesc(){
        return ResponseEntity.ok().body(productService.findAllByPriceDesc());
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Product product){
        Product responseProduct = productService.saveProduct(product);
        return ResponseEntity.ok().body(responseProduct);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        Product responseProduct = productService.updateProduct(product);
        return ResponseEntity.ok().body(responseProduct);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    @PostMapping("/searchByImage")
    public ResponseEntity<List<Product>> searchProductByImage(@RequestBody String imageUrl) {
        // Gửi yêu cầu đến Backend Python để xử lý
        String pythonApiUrl = "http://localhost:5000/searchByImage"; // Địa chỉ API Python
        RestTemplate restTemplate = new RestTemplate();

        // Gửi đường link ảnh đến Backend Python
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(imageUrl, headers);

        List<String> responseImgUrl = restTemplate.postForEntity(pythonApiUrl, request, List.class).getBody();

        List<Product> result = new ArrayList<>();
        if(responseImgUrl != null){
            List<Product> productList = productService.getAllProducts();
            for(Product product : productList ){
                for(String imgUrl : responseImgUrl){
                    if(product.getImages().contains(imgUrl)){
                        result.add(product);
                        break;
                    }
                }
            }
        }
        // Trả về kết quả từ Backend Python cho Frontend ReactJS
        return ResponseEntity.ok().body(result);
    }
    
}
