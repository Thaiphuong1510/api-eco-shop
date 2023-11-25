package com.example.ecoshop.Service;

import com.example.ecoshop.Model.Category;
import com.example.ecoshop.Model.User;
import com.example.ecoshop.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
// lấy danh sách category đang hoạt động
    public List<Category> getCategoriesByActive(){
        return categoryRepository.findAllByActive();
    }
    public Category getCategoryById(int id){
        return categoryRepository.findById(id).get();
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        Category existingCategory = categoryRepository.findById(category.getId()).orElse(null);
        existingCategory.setNameCategory(category.getNameCategory());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setStatus(category.getStatus());
        return categoryRepository.save(existingCategory);
    }

    public String deleteCategory(int id){
        categoryRepository.deleteById(id);
        return "User removed " + id;
    }



}
