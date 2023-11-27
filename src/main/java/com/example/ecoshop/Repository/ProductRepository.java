package com.example.ecoshop.Repository;

import com.example.ecoshop.Model.Category;
import com.example.ecoshop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product  findAllByNameProduct(String nameProduct);
    @Query(value = "SELECT * FROM ecoshop.tbl_product WHERE `status` = 1;", nativeQuery = true)
    List<Product> findAllByActive();
    @Query(value = "SELECT * FROM ecoshop.tbl_product where tbl_product.name_product like %:nameProduct%", nativeQuery = true)
    List<Product> findBySearchNameProduct(@Param("nameProduct") String nameProduct);
    List<Product> findAllByCategory(Category category);
    @Query(value = "SELECT * FROM ecoshop.tbl_product where tbl_product.unit_price > :bot_price and tbl_product.unit_price < :top_price", nativeQuery = true)
    List<Product> findByPrice(float bot_price, float top_price);

//    @Query(value = "SELECT * FROM ecoshop.tbl_product where tbl_product.unit_price > :price1 and tbl_product.unit_price < :price2", nativeQuery = true)
//    List<Product> findByFilter(String float price1, float price2);

}
