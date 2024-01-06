package com.example.ecoshop.Repository;

import com.example.ecoshop.Model.Staff;
import com.example.ecoshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
    Staff findStaffByUser(User user);
}
