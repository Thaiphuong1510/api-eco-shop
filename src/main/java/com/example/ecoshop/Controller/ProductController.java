package com.example.ecoshop.Controller;

import com.example.ecoshop.DTO.FilterDTO;
import com.example.ecoshop.Model.Product;
import com.example.ecoshop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
