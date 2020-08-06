package com.quang.spring.demo.controller;

import com.quang.spring.demo.dto.CustomUserDetails;
import com.quang.spring.demo.dto.LoginResponeDto;
import com.quang.spring.demo.dto.UserDto;
import com.quang.spring.demo.service.UserService;
import com.quang.spring.demo.utils.TokenAuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
   @Autowired
   UserService userService;

   @Autowired
   AuthenticationManager authenticationManager;

   @GetMapping(value = "/user/insert/{username}/{password}")
   public @ResponseBody
   String insertUser (@PathVariable String username, @PathVariable String password){
      userService.insertUser(username, password);
      return "";
   }

   @GetMapping(value = "/user/get/{username}/{password}")
   public @ResponseBody
   ResponseEntity getUser (@PathVariable String username, @PathVariable String password){
      return ResponseEntity.ok(userService.findUser(username, password));
   }

   @GetMapping(value = "/user/get/{userId}")
   public @ResponseBody
   ResponseEntity<UserDto> getUserById (@PathVariable Long userId){

      return ResponseEntity.ok(userService.getUserById(userId));
   }

   @GetMapping(value = "/login/{username}/{password}")
   public @ResponseBody
   ResponseEntity<LoginResponeDto> login (@PathVariable String username, @PathVariable String password) throws Exception {
      try {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

         UserDetails userDetails = userService.loadUserByUsername(username);
         String token = TokenAuthenticationUtils.generateToken(userDetails);
         LoginResponeDto loginResponeDto = new LoginResponeDto(username, ((CustomUserDetails) userDetails).getUser().getId(), token);
         System.out.println("Login");
         return ResponseEntity.ok(loginResponeDto);
      } catch (DisabledException e) {
         throw new Exception("USER_DISABLED", e);
      } catch (BadCredentialsException e) {
         throw new Exception("INVALID_CREDENTIALS", e);
      }
   }

}
