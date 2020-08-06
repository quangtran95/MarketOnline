package com.quang.spring.demo.dto;

public class LoginResponeDto {
   String userName;
   Long userId;
   String token;

   public LoginResponeDto(String userName, Long userId, String token) {
      this.userName = userName;
      this.userId = userId;
      this.token = token;
   }

   public LoginResponeDto() {
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }
}
