package com.americanbanksystems.wiki.web;

/**
 * 
 * @Author BorisM
 * @Date 10.25.2014
 * 
 * */

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.americanbanksystems.wiki.dao.ProductDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.Product;
import com.americanbanksystems.wiki.exception.UserDeleteException;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;

@Controller
@RequestMapping("/products")
public class ProductController implements BaseController {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
    private SecurityServiceBean security;
		
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
    public String showProducts(Model model) {    	
    	
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	
        List<Product> products = productDao.list();
        model.addAttribute("products", products);
 
        return "products/listProducts";
    }
	
	//VIEW PRODUCT - GET
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String getProduct(@PathVariable("id") long id, Model model) {
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	
	    Product product = productDao.findProduct(id);
	    model.addAttribute("product", product);
	 
	    return "products/viewProduct";
	}
	
	//UPDATE PRODUCT - POST
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String updateProduct(@PathVariable("id") long id, @Valid @ModelAttribute Product product, BindingResult errors) {
		
		//This method is validated with JSR303 and Hibernate Validator implementation. In case JavaScript on the front end is disabled -NOT LIKELY
		//@Valid validates the data, BindingResults hold any errors
		if(errors.hasErrors()) {					
			return "products/viewProduct";
		}
		
		product.setId(id);
		
		//Product Identity is set here. Should be the same as product name with underscore instead of space
		String productIdentity  = product.getProductName().replaceAll(" ", "_");
		product.setProductIdentity(productIdentity);
		
		//Set CUSTOM to true
		product.setCustom(true);
		productDao.updateEntity(product);
		logger.info("Product ["+product.getProductName()+"] updated!");
	    return "redirect:/products";
	}
	
	//DELETE PRODUCT - DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteProduct(@PathVariable("id") long id)
	        throws UserDeleteException {
	 
		boolean wasDeleted = false;
		
		Product toDelete = productDao.findProduct(id);
		String productName = toDelete.getProductName();
		if(productDao.productDeletable(toDelete)) wasDeleted = productDao.removeEntity(toDelete);	    
	 
	    if (!wasDeleted) {
	      //  throw new UserDeleteException(toDelete);
	    	logger.error("Product ["+productName+"] was NOT deleted!");
	    } else {
	    	logger.info("Product ["+productName+"] was deleted!");
	    }	 
	    // everything OK, see remaining employees
	    return "redirect:/products";
	}
	
	//CREATE A NEW PRODUCT - GET
	@RequestMapping(params = "new", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String createProductForm(Model model) {    	
    	
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
		model.addAttribute("loggedUser", security.getLoggedInUser());
		
	    model.addAttribute("product", new Product());
	    return "products/newProduct";
	}
	
	//CREATE A NEW PRODUCT - POST
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String addProduct(@Valid @ModelAttribute Product product, BindingResult errors) {
		if(errors.hasErrors()) {			
			return "products/newProduct";
		}
		
		//Set Producty Identity
		String productIdentity  = product.getProductName().replaceAll(" ", "_");
		product.setProductIdentity(productIdentity);
		
		//Set CUSTOM to true
		product.setCustom(true);
		
		productDao.addEntity(product);
		logger.info("Product ["+product.getProductName()+"] created!");
	    return "redirect:/products";
	}
	
	//Not user but left here for future reference
	@ExceptionHandler(UserDeleteException.class)
	public ModelAndView handleDeleteException(UserDeleteException e) {
	    ModelMap model = new ModelMap();
	    model.put("user", e.getUser());
	    return new ModelAndView("users/delete-error", model);
	}	
}