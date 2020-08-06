package com.quang.spring.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
   @Id
   @GeneratedValue
   @Column(name="USER_ID")
   private Long id;

   @Column(name="USER_NAME")
   private String username;

   @Column(name="PASSWORD")
   private String password;

   @Column(name="FIRST_NAME")
   private String firstName;

   @Column(name="LAST_NAME")
   private String lastName;

   @Column(name="PHONE_NUMBER")
   private String phoneNumber;

   @Column(name="ADDRESS")
   private String address;

   public User(String username, String password) {
      this.username = username;
      this.password = password;
   }

   public User() {
   }

   public User(String username, String password, String firstName, String lastName, String phoneNumber, String address) {
      this.username = username;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.address = address;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }
}
