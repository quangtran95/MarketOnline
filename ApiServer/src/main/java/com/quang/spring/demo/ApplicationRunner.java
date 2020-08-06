package com.quang.spring.demo;

import com.quang.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements org.springframework.boot.ApplicationRunner {
   @Autowired
   private UserService userService;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public void run(ApplicationArguments args) throws Exception {
      userService.insertUser2("tom", passwordEncoder.encode("123"), "Quang", "Tran", "753/16/19", "094646");
      System.out.println("Insert user: tom");
   }
}
