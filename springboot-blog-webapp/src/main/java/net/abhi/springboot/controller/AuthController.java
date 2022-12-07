package net.abhi.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.abhi.springboot.dto.RegistrationDto;
import net.abhi.springboot.entity.User;
import net.abhi.springboot.service.UserService;

@Controller
public class AuthController {
	
	private UserService userService;
	
	@Autowired
   public AuthController(UserService userService) {
		
		this.userService = userService;
	}


//handler method to handle user Registration request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	//handler method to handle the login page request
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") RegistrationDto user,
			                BindingResult result, Model model) {
		User existingUser= userService.findByEmail(user.getEmail());
		
		if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "There's already a user with same email");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		
		userService.saveUser(user);
		return "redirect:/register?success";
	}
	
}
