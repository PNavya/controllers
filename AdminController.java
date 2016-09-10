package com.niit.ShoppingCart.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.ShoppingCart.dao.CategoryDAO;
import com.niit.ShoppingCart.dao.ProductDAO;
import com.niit.ShoppingCart.dao.SupplierDAO;
import com.niit.ShoppingCart.dao.UserDetailsDAO;
import com.niit.ShoppingCart.model.Category;
import com.niit.ShoppingCart.model.Product;
import com.niit.ShoppingCart.model.Supplier;
import com.niit.ShoppingCart.model.UserDetails;



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
	private UserDetails userdetails;
	
	@Autowired
	private UserDetailsDAO userDetailsDAO;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	
	@RequestMapping("/check")
	public ModelAndView Signin(@ModelAttribute("userdetails")UserDetails user ,BindingResult result,HttpServletRequest request)throws IOException
	{
		System.out.println(user.getName());
		System.out.println("In Checking Sigin Page");
		ModelAndView mv =null;
		/*String msg;*/
		
		if(userDetailsDAO.isValidUser(user.getName(),user.getPassword())==null)
		{
/*			msg="Invalid User...please try again";*/
		}
		else {
			userdetails=userDetailsDAO.isValidUser(user.getName(),user.getPassword());
		if((userdetails.getRole().equals("ROLE_ADMIN"))){
			
			mv = new ModelAndView("adminhome");
			
		}else{
			return new ModelAndView("bootstrap");
			
		}
		}	
		return mv;
	}
	
	
	
	
	@RequestMapping("/addProduct" )
	public ModelAndView addp()
	{
		System.out.println("in add prod page");
		ModelAndView mv = new ModelAndView("addProduct");
		return mv;
	}
	@ModelAttribute
	public Product returnObject()
	{
		return new Product();
	}
	
	
	@RequestMapping(value="/addprod", method = RequestMethod.POST)
	public ModelAndView Productregister(@Valid @ModelAttribute("product") Product prod, BindingResult result,
						HttpServletRequest request) throws IOException {
					ModelAndView mv = new ModelAndView("bootstrap");
			
					@SuppressWarnings("unused")
					String filename;
					@SuppressWarnings("unused")
					byte[] bytes;
					System.out.println(prod.getName());
			
				System.out.println("image uploaded");
			
					System.out.println("myproduct controller called");
					MultipartFile image = prod.getImage();
					Path path;
					path = Paths.get(
							"E://maven//ShoppingCart1//src//main//webapp//resources//images//" + prod.getProdid() + ".jpg");
			
					System.out.println("Path = " + path);
					System.out.println("File name = " + prod.getImage().getOriginalFilename());
					if (image != null && !image.isEmpty()) {
						try {
							image.transferTo(new File(path.toString()));
							System.out.println("Image Saved in:" + path.toString());
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Image not saved");
						}
					}
					productDAO.save(prod);
					return mv;
				}
	
	@RequestMapping("/addCategory" )
	public ModelAndView add()
	{
		ModelAndView mv = new ModelAndView("addCategory");
		return mv;
	}
	@ModelAttribute
	public Category returnCat()
	{
		return new Category();
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ModelAndView addCategories(@ModelAttribute("category")Category cat,BindingResult result,HttpServletRequest request)throws IOException
	{
		
		ModelAndView mv=new ModelAndView("adminhome");
		
		categoryDAO.save(cat);
		//log.debug("ending of the method addcategories");
		return mv;
		
	}
	@RequestMapping("/addSupplier" )
	public ModelAndView supp()
	{
		ModelAndView mv = new ModelAndView("addSupplier");
		return mv;
	}
	@ModelAttribute
	public Supplier returnSup()
	{
		return new Supplier();
	}
	
	@RequestMapping(value="/addsupp",method=RequestMethod.POST)
	public ModelAndView addSupplier(@ModelAttribute("supplier")Supplier sup,BindingResult result,HttpServletRequest request)throws IOException
	{
		
		ModelAndView mv=new ModelAndView("adminhome");
		
		supplierDAO.save(sup);
		//log.debug("ending of the method addcategories");
		return mv;
		
	}
	@RequestMapping("/updateCategory" )
	public ModelAndView update()
	{
		ModelAndView mv = new ModelAndView("updateCategory");
		return mv;
	}
	@ModelAttribute
	public Category returnUpdate()
	{
		return new Category();
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ModelAndView updateCategory(@ModelAttribute("category")Category cat,BindingResult result,HttpServletRequest request)throws IOException
	{
		
		ModelAndView mv=new ModelAndView("adminhome");
		
		categoryDAO.update(cat);
		
		return mv;
		
	}
	@RequestMapping("/updateSupplier" )
	public ModelAndView supplier()
	{
		ModelAndView mv = new ModelAndView("updateSupplier");
		return mv;
	}
	@ModelAttribute
	public Supplier returnSupp()
	{
		return new Supplier();
	}
	
	@RequestMapping(value="/updatesup",method=RequestMethod.POST)
	public ModelAndView updateSupplier(@ModelAttribute("supplier")Supplier sup,BindingResult result,HttpServletRequest request)throws IOException
	{
		
		ModelAndView mv=new ModelAndView("adminhome");
		
		supplierDAO.update(sup);
		//log.debug("ending of the method addcategories");
		return mv;
		
	}
	@RequestMapping("/updateProduct" )
	public ModelAndView updateprod()
	{
		ModelAndView mv = new ModelAndView("updateProduct");
		return mv;
	}
	@ModelAttribute
	public Product returnp()
	{
		return new Product();
	}
	
	
	@RequestMapping(value="/updateprod",method=RequestMethod.POST)
	public ModelAndView updateP(@ModelAttribute("product")Product p2,BindingResult result,HttpServletRequest request)throws IOException
	{
		
		ModelAndView mv=new ModelAndView("adminhome");
		
		productDAO.update(p2);
		
		return mv;
		
	}
	@RequestMapping("/remove" )
	public ModelAndView delsupplier()
	{
		ModelAndView mv = new ModelAndView("remove");
		return mv;
	}
	@ModelAttribute
	public Supplier returndel()
	{
		return new Supplier();
	}
	@RequestMapping("delete")
	public ModelAndView removeSupplier(@ModelAttribute("supplier")Supplier sup,@RequestParam("supId") String supId,BindingResult result) throws Exception {
	
		//supplier = supplierDAO.get(supId);
		
		ModelAndView mv = new ModelAndView("adminhome");
		
		if(supplier==null)
			
		{
			mv.addObject("error messaege","could not delete the supplier");
		}
		else
		{
			supplierDAO.delete(sup);
		}
		return mv;
	}
	@RequestMapping("/deleteCategory" )
	public ModelAndView delCategory()
	{
		ModelAndView mv = new ModelAndView("deleteCategory");
		return mv;
	}
	@ModelAttribute
	public Category returncate()
	{
		return new Category();
	}
	@RequestMapping("removecat")
	public ModelAndView del(@ModelAttribute("category")Category cat,BindingResult result) throws Exception
	{
		//category=categoryDAO.get(catid);
		ModelAndView mv=new ModelAndView("adminhome");
		if(category==null)
		{
			mv.addObject("errorMessage", "could not delete the category");
		}
		else
		{
			categoryDAO.delete(cat);
		}
		return mv;
	}
	@RequestMapping("/deleteProduct" )
	public ModelAndView delProduct()
	{
		ModelAndView mv = new ModelAndView("deleteProduct");
		return mv;
	}
	@ModelAttribute
	public Product returnprd()
	{
		return new Product();
	}
	@RequestMapping("removeprod")
	public ModelAndView delP(@ModelAttribute("product")Product prod,BindingResult result) throws Exception
	{
		
		ModelAndView mv=new ModelAndView("adminhome");
		if(product==null)
		{
			mv.addObject("errorMessage", "could not delete the category");
		}
		else
		{
			productDAO.delete(prod);
		}
		return mv;
	}
	/*@RequestMapping("/manageCategories")
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
		*/
	}
	
	
	
	
	
	


