package com.example.ecoshop.Repository;

import com.example.ecoshop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM tblUser WHERE name LIKE %:name%", nativeQuery = true)
    List<User> findAllByName(@Param("name") String name);
    User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);
   // List<User> findAllByRole(Integer role);


}
