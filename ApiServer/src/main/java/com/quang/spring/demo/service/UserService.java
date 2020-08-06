package com.quang.spring.demo.service;

import com.quang.spring.demo.dto.CustomUserDetails;
import com.quang.spring.demo.dto.UserDto;
import com.quang.spring.demo.model.User;
import com.quang.spring.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService implements UserDetailsService {
   @Autowired
   UserDao userDao;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userDao.findUserByUsername(username);
      if (user == null) {
         throw new UsernameNotFoundException(username);
      }
      return new CustomUserDetails(user);
   }

   public boolean findUser(String username, String password){
      User user = userDao.findUserByUsernameAndAndPassword(username, password);
      if(user != null){
         return true;
      }
      return false;
   }

   public void insertUser(String username, String password){
      User user = userDao.save(new User(username, password));
      System.out.println("Insert User Id: " + user.getId());
   }

   public void insertUser2(String username, String password, String firstName, String lastName, String address, String phoneNumber){
      User user = userDao.save(new User(username, password, firstName, lastName, phoneNumber, address ));
      System.out.println("Insert User Id: " + user.getId());
   }

   public void updateUser(UserDto userDto){
      User user = userDao.findUserById(userDto.getId());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setAddress(userDto.getAddress());
      user.setPhoneNumber(userDto.getPhoneNumber());
     userDao.save(user);
      System.out.println("Save User Id: " + user.getId());
   }

   public UserDto getUserById(Long userId){
      User user = userDao.findUserById(userId);
      if(user != null){
         UserDto userDto = mapper.map(user, UserDto.class);
         return userDto;
      }
      return null;
   }
}
