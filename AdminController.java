package com.niit.ShoppingCart.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.dao.CategoryDAO;
import com.niit.ShoppingCart.dao.ProductDAO;
import com.niit.ShoppingCart.dao.SupplierDAO;
import com.niit.ShoppingCart.model.Category;
import com.niit.ShoppingCart.model.Product;
import com.niit.ShoppingCart.model.Supplier;



@Controller
public class AdminController {
	@Autowired
	private Category category;
	
	@Autowired
	private Product product;
	
	@Autowired
	private Supplier supplier;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	@RequestMapping("/manageCategories")
	public ModelAndView Categories(){
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("Category", category);
		mv.addObject("isAdminClickedCategories","true");
		mv.addObject("CategoryList", categoryDAO.list());
		return mv;
	}
	
	@RequestMapping("/manageProducts")
	public ModelAndView Products(){
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("Product", product);
		mv.addObject("isAdminClickedProducts","true");
		mv.addObject("ProductList", productDAO.list());
		return mv;
	}
	
	@RequestMapping("/manageSuppliers")
	public ModelAndView Suppliers(){
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("Supplier", supplier);
		mv.addObject("isAdminClickedSuppliers","true");
		mv.addObject("SupplierList", supplierDAO.list());
		return mv;
	}
		
	}
	
	
	
	
	
	


