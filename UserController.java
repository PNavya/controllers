package com.niit.ShoppingCart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.dao.UserDetailsDAO;
import com.niit.ShoppingCart.model.UserDetails;

@Controller
public class UserController {

	// when the user clicked login
	// based on the credentials,I want find whether he is admin or not
	// if he is admin,I want to navigate to adminHome page
	// if he is user,I want to navigate to home page
	// if the user does not exist with this credentials,i want to give error
	// messages
	@Autowired
	UserDetailsDAO userDetailsDAO;

	@Autowired
	UserDetails userDetails;

	@RequestMapping
	public ModelAndView login(@RequestParam(value="id") String id,@RequestParam(value="password") String password,HttpSession session)
	{
		ModelAndView mv=new ModelAndView("home");
		String msg;
		userDetails=userDetailsDAO.isValidUser(id,password);
		if(userDetails==null)
		{
			msg="Invalid User...please try again";
		}
		else
		{
			//find out whether the user is admin or not
			if(userDetails.getRole().equals("ROLE_ADMIN"))
			{
				mv=new ModelAndView("adminHome");
				session.setAttribute("WelcomeMsg",userDetails.getName());
				session.setAttribute("userId",userDetails.getId());
			}
			else
			{
				mv.addObject("WelcomeMsg",userDetails.getName());
			}
		}
		return mv;
	}
	
	
	//logout also

}
