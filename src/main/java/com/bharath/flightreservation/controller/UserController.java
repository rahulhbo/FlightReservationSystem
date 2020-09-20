package com.bharath.flightreservation.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entities.User;
import com.bharath.flightreservation.repos.UserRepository;
import com.itextpdf.text.log.LoggerFactory;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepositer;
	
	@Autowired
	private static final Logger LOGGER= org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/showReg")
	public String showRegistrationPage()
	{
		LOGGER.info("Inside showRegistrationPage()");
		
		return "login/registerUser";
	}
	
	
	@RequestMapping("/showLogin")
	public String showLoginPage()
	{
		LOGGER.info("Inside showLoginPage()");
		
		return "login/login";
	}
	
	
	
	@RequestMapping(value="/registerUser" , method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user)
	{
		LOGGER.info("Inside register()"+user);
		userRepositer.save(user);
		
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email")String email, @RequestParam("password")String password, ModelMap modelMap)
	{
		
		LOGGER.info("Inside Login() and the Email:"+email);
		
		User user=userRepositer.findByEmail(email);
		if(user.getPassword().equals(password))
		{
			return "findFlights";
		}
		else
		{
			modelMap.addAttribute("msg","Invalid User name or password, Please Try again .");
		}
		return "login/login";
	}
}
