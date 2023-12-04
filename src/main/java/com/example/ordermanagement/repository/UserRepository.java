package com.example.ordermanagement.repository;


import com.example.ordermanagement.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUserName(String user);
   Optional<User> findByEmail(String email);

   @Query("select u from User u where u.firstName=?1 and u.lastName=?2")
   User findByJPQL(String firstName, String lastName);

   @Query("select u from User u where u.firstName=:firstName and u.lastName=:lastName")
   User findByJPQLNamesParams(@Param("firstName")String firstName, @Param("lastName") String lastName);

   @Query(value = "select * from user u where u.first_name=?1 and u.last_name=?2",nativeQuery = true)
   User findByNativeSQL(String firstName, String lastName);

   @Query(value = "select * from user u where u.first_name=?1 and u.last_name=?2 ",nativeQuery = true)
   User findByNativeSQLNamed(String firstName, String lastName);
}
