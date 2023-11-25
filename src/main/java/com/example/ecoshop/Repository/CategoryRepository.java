package com.example.ecoshop.Repository;

import com.example.ecoshop.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM ecoshop.tbl_category WHERE `status` = 1;", nativeQuery = true)
    List<Category> findAllByActive();

}
