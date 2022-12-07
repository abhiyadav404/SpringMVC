package net.abhi.springboot.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.abhi.springboot.dto.RegistrationDto;
import net.abhi.springboot.entity.Role;
import net.abhi.springboot.entity.User;
import net.abhi.springboot.repository.RoleRepository;
import net.abhi.springboot.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
    
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
	
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public void saveUser(RegistrationDto registrationDto) {
	    
		User user = new User();
		user.setName(registrationDto.getFirstName()+" "+registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		
		Role role = roleRepository.findByName("ROLE_GUEST");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}



	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

}
