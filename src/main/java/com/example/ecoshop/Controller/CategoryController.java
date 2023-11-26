package com.example.ecoshop.Controller;

import com.example.ecoshop.Model.Cart;
import com.example.ecoshop.Model.Category;
import com.example.ecoshop.Model.User;
import com.example.ecoshop.Service.CartService;
import com.example.ecoshop.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getCategories());
    }
    @GetMapping("/categoriesByActive")
    public ResponseEntity<List<Category>> getAllCategoriesByActive(){
        return ResponseEntity.ok().body(categoryService.getCategoriesByActive());
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<?>  getCategoryById(@PathVariable int id){
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category responseCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok().body(responseCategory);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody Category category){
        Category responseCategory = categoryService.updateCategory(category);
        return ResponseEntity.ok().body(responseCategory);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }



}
