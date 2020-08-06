package com.quang.spring.demo.repository;

import com.quang.spring.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
   User findUserByUsernameAndAndPassword(String username, String password);
   User findUserByUsername(String username);
   User findUserById(Long userId);
}
