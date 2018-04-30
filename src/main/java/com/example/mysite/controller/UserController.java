package com.example.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mysite.domain.User;
import com.example.mysite.service.UserService;
import com.example.security.Auth;
import com.example.security.AuthUser;


@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping( value="/join", method=RequestMethod.GET )
	public String join( @ModelAttribute User user ){
		return "user/join";
	}
	
	@RequestMapping( value="/join", method=RequestMethod.POST )
	public String join( 
		@ModelAttribute @Valid User user,
		BindingResult result,
		Model model ){
		
		if( result.hasErrors() ) {
			model.addAllAttributes( result.getModel() );
			return "user/join";
		}
		
		userService.join( user );
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}

	@RequestMapping( "/joinsuccess" )
	public String joinsuccess(){
		return "user/joinsuccess";
	}
	
	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.GET )
	public String modify( 
		@AuthUser User authUser,	
		Model model ){
		User user = userService.getUser( authUser.getNo() );
		model.addAttribute( "user", user );
		return "user/modify";
	}
	
	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.POST )
	public String modify( 
		@AuthUser User authUser,
		@ModelAttribute User user ){

		user.setNo( authUser.getNo() );
		userService.modifyUser( user );
		
		return "redirect:/user/modify?result=success";
	}
}
