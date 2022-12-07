package net.abhi.springboot.service;


import net.abhi.springboot.dto.RegistrationDto;
import net.abhi.springboot.entity.User;

public interface UserService {
   void saveUser(RegistrationDto registrationDto);

   User findByEmail(String email);
}
