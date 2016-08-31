package com.niit.ShoppingCart.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.dao.CategoryDAO;
import com.niit.ShoppingCart.model.Category;

@Controller
public class CategoryController {
	
	private static Logger log=LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private Category category;
	
	@RequestMapping(value="/categories",method=RequestMethod.GET)
	public String listCategories(Model model)
	{
		log.debug("Starting of the method listcategories");
		model.addAttribute("category",category);
		model.addAttribute("categoryList",categoryDAO.list());
		log.debug("ending of the method listcategories");
		return "category";
		
	}

	@RequestMapping(value="/category/add",method=RequestMethod.POST)
	public String addCategories(Model model)
	{
		log.debug("Starting of the method addcategories");
		//saveorupdate-if it exist, it will override else will create new record
		//save-if it exist,will give error.else it will create new record
		//update-if it does not exist will give error.else it will update
		//you have only save method.
		//check if the record exist or not
		//if the record exist,given error message to user "the record exist with this id"
		//else create the record.
		ModelAndView mv=new ModelAndView();
		if(categoryDAO.get(category.getCatid())==null)
		{
			categoryDAO.save(category);
		}
		else
		{
			mv.addObject("error message","The record exist with this id" +category.getCatid());
		}
		//categoryDAO.saveOrUpdate(category);
		log.debug("ending of the method addcategories");
		return "category";
		
	}
	//http://localhost:8080/ShoppingCart/category/remove/CG101

	@RequestMapping("category/remove/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") int id) throws Exception
	{
		category=categoryDAO.get(id);
		ModelAndView mv=new ModelAndView("category");
		if(category==null)
		{
			mv.addObject("errorMessage", "could not delete the category");
		}
		else
		{
			categoryDAO.delete(category);
		}
		/*boolean flag=categoryDAO.delete(id);
		ModelAndView mv=new ModelAndView("category");
		String msg="Successfully done the operation";
		if(flag!=true)
		{
			msg="The operation could not success";
		}
		mv.addObject("msg", msg);*/
		return mv;
	}

	@RequestMapping(value="category/edit/{id}")
	public String editCategory(@ModelAttribute("category") Category category)
	{
		//check whether category exist with this id or not
		//if does not exist,given error message
		//if it is exist,you update the existing category
		
		ModelAndView mv=new ModelAndView();

		if(categoryDAO.get(category.getCatid())!=null)
		{
			categoryDAO.update(category);
			mv.addObject("message","Successfully updated");
		}
		else
		{
			mv.addObject("errorMessage","could update the record");
		}
		
		return "category";
		
	}
	

}
