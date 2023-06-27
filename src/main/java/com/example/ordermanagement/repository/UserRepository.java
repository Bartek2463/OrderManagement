package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

   Optional<Users> findByUserName(String user);
   Optional<Users> findByEmail(String email);





}
