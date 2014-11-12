package com.americanbanksystems.wiki.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class ProductController {
	
	private ProductDao productDao;
	
	@Autowired
    private SecurityServiceBean security;
	 
	@Autowired
	public void setProductDao(ProductDao productDao) {
	    this.productDao = productDao;
	}
	
	private UserDao userDao;
	 
	@Autowired
	public void setUserDao(UserDao userDao) {
	    this.userDao = userDao;
	}

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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String updateProduct(@PathVariable("id") long id, Product product) {
		product.setId(id);
		String productIdentity  = product.getProductName().replaceAll(" ", "_");
		product.setProductIdentity(productIdentity);
		product.setCustom(true);
		productDao.updateEntity(product);
	 
	    return "redirect:/products";
	}
	
	//Delete Product
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteProduct(@PathVariable("id") long id)
	        throws UserDeleteException {
	 
		boolean wasDeleted = false;
		
		Product toDelete = productDao.findProduct(id);
		if(productDao.productDeletable(toDelete)) wasDeleted = productDao.removeEntity(toDelete);	    
	 
	    if (!wasDeleted) {
	      //  throw new UserDeleteException(toDelete);
	    }
	 
	    // everything OK, see remaining employees
	    return "redirect:/products";
	}
	
	@RequestMapping(params = "new", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String createProductForm(Model model) {    	
    	
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
		model.addAttribute("loggedUser", security.getLoggedInUser());
		
	    model.addAttribute("product", new Product());
	    return "products/newProduct";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String addProduct(Product product) {
		String productIdentity  = product.getProductName().replaceAll(" ", "_");
		product.setProductIdentity(productIdentity);
		product.setCustom(true);
		
		productDao.addEntity(product);
	 
	    return "redirect:/products";
	}
	
	
	@ExceptionHandler(UserDeleteException.class)
	public ModelAndView handleDeleteException(UserDeleteException e) {
	    ModelMap model = new ModelMap();
	    model.put("user", e.getUser());
	    return new ModelAndView("users/delete-error", model);
	}
	
}
