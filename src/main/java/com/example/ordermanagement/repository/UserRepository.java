package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findById(Long id);
}
