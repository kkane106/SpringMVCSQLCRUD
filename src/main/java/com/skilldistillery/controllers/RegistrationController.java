package com.skilldistillery.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.data.AuthenticationDAO;
import com.skilldistillery.data.User;


@SessionAttributes({"user"})
@Controller
public class RegistrationController {
	
	@Autowired
	private AuthenticationDAO authDao;
	
	@RequestMapping(path="createUser.do", method=RequestMethod.GET)
	public ModelAndView register() {
		User user = new User();
		ModelAndView mv = new ModelAndView("signup.jsp","user", user);
		return mv;
	}
	
	@RequestMapping(path="createUser.do", method=RequestMethod.POST)
	public String create(@Valid User user, Errors errors) {
		System.out.println("in create user");
		System.out.println(user);
		if (!authDao.usernameIsUnique(user.getUsername())) {
			errors.rejectValue("email", user.getUsername());
		}
		if (errors.getErrorCount() != 0) {
			return "signup.jsp";
		}
		
		else{
			authDao.createUser(user);
		}
		return "yourConcertsPage.jsp";
	}
}

