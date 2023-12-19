package com.example.ecoshop.Service;

import com.example.ecoshop.DTO.FilterDTO;
import com.example.ecoshop.Model.Product;
import com.example.ecoshop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getProductsByActive(){
        return productRepository.findAllByActive();
    }
    public Product getProductById(int id){
        return productRepository.findById(id).get();
    }
    public Product getProductByName(String name){
        return productRepository.findAllByNameProduct(name);
    }
    public List<Product> findBySearchNameProduct(String name){
        return productRepository.findBySearchNameProduct(name);
    }

    public List<Product> findByPrice(float bot_price, float top_price){
        return productRepository.findByPrice(bot_price, top_price);
    }

    public List<Product> getProductByFilter(FilterDTO filterDTO){
        List<Product> productsByCategory = productRepository.findAllByCategory(filterDTO.getCategory());
        List<Product> productsByPrice = productRepository.findByPrice(filterDTO.getBot_price(), filterDTO.getTop_price());

        productsByPrice.retainAll(productsByCategory);//hàm giao 2 list
        return productsByPrice;
    }
    public List<Product> findAllByPriceAsc(){
        return productRepository.findAllByPriceAsc();
    }
    public List<Product> findAllByPriceDesc(){
        return productRepository.findAllByPriceDesc();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setNameProduct(product.getNameProduct());
        existingProduct.setUnitPrice(product.getUnitPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImages(product.getImages());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStatus(product.getStatus());
        return productRepository.save(existingProduct);
    }

    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return "User removed " + id;
    }


}
