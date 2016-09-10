package com.niit.ShoppingCart.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.dao.UserDetailsDAO;
import com.niit.ShoppingCart.model.UserDetails;

//import com.niit.ShoppingCart.model.Product;

@Controller
public class HomeController {
	
	
	//@Autowired
	//private UserDetails userDetails;
	@Autowired
	private UserDetailsDAO userdetailsDAO;
	
	
	@RequestMapping("/")
	public ModelAndView showHome()
	{
		ModelAndView mv=new ModelAndView("bootstrap");
		return mv;
	}

	@RequestMapping("/Login")
	public ModelAndView showSignin()
	{
		ModelAndView mv=new ModelAndView("Login");
		return mv;
	}

	@RequestMapping("/signup")
	public ModelAndView showSignUp()
	{
		System.out.println("in signup");
		//model.addAttribute(attributeName, attributeValue)
		return new ModelAndView("signup");
	}
	@ModelAttribute
	public UserDetails returnObject()
	{
		return new UserDetails();
	}
	
	@RequestMapping("/register")
	public ModelAndView register(@Valid @ModelAttribute("userDetails")UserDetails user,BindingResult result,HttpServletRequest request)throws IOException
	{
		System.out.println(user.getConfirmpassword());
		System.out.println(user.getPassword());

		ModelAndView mv=new ModelAndView("Login");
		user.setRole("ROLE_USER");
		if(user.getConfirmpassword().equals(user.getPassword()))
		{
			userdetailsDAO.save(user);
		}
		//mv.addObject("UserDetails",userDetails);
		//mv.addObject("isUserClickRegisterHere","true");
		

		
		return mv;
	}
	
	/*@RequestMapping("/addProduct")
	public ModelAndView showHome(@ModelAttribute("product")Product product,BindingResult result,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView("addProduct");
		return mv;
	}*/
	/*@RequestMapping("/check")
	public ModelAndView Signin(@RequestParam ("name")String name,@RequestParam("password") String password)
	{
		System.out.println(name);
		boolean b;
		System.out.println("In Checking Sigin Page");
		ModelAndView mv = new ModelAndView ("home");
		
		if((name.equals("navya"))&&(password.equals("navya"))){
			b=true;
			
			
		}else{
			System.out.println("invalid user plese enter  valid credentials ");
			b=false;
		}
		
		if(b==true)
		{
			mv = new ModelAndView("adminhome");
		}
		else
		{
			return new ModelAndView("bootstrap");
		}
		return mv;
	}*/
}
