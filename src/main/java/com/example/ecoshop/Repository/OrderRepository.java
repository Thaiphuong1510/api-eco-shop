package com.example.ecoshop.Repository;

import com.example.ecoshop.Model.Order;
import com.example.ecoshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCreatAtBetween(LocalDate startDate, LocalDate endDate);

    List<Order> findAllByUser(User user);
}
