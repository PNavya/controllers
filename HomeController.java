package com.niit.ShoppingCart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.model.Product;
import com.niit.ShoppingCart.model.UserDetails;

//import com.niit.ShoppingCart.model.Product;

@Controller
public class HomeController {
	
	
	@Autowired
	private UserDetails userDetails;
	
	
	@RequestMapping("/")
	public ModelAndView showHome()
	{
		ModelAndView mv=new ModelAndView("bootstrap");
		return mv;
	}

	
	@RequestMapping("/register")
	public ModelAndView register()
	{
		ModelAndView mv=new ModelAndView("register");
		mv.addObject("UserDetails",userDetails);
		mv.addObject("isUserClickRegisterHere","true");

		
		return mv;
	}
	@RequestMapping("/Login")
	public ModelAndView showLogin()
	{
		ModelAndView mv=new ModelAndView("Login");
		return mv;
	}
	/*@RequestMapping("/addProduct")
	public ModelAndView showHome(@ModelAttribute("product")Product product,BindingResult result,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("addProduct");
		return mv;
	}*/
	
}
