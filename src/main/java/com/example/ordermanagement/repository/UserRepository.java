package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUserName(String user);
   Optional<User> findByEmail(String email);

}
