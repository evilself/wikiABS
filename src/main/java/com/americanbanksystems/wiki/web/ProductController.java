package com.americanbanksystems.wiki.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.exception.UserDeleteException;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private ProductDao productDao;
	 
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
    public String showProducts(Model model, Principal principal) {    	
    	
    	User loggedInUser;
    	if  (null != principal) {
    		loggedInUser = userDao.findUserByUsername(principal.getName());
    	} else {
    		loggedInUser = null;
    	}
    	model.addAttribute("loggedUser",loggedInUser);
    	
    	model.addAttribute("admin","false");
    	List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    	for(GrantedAuthority ga: authorities) {
    		if ((ga.getAuthority()).equalsIgnoreCase("admin")) model.addAttribute("admin","true");
    	}
    	
        List<Product> products = productDao.list();
        model.addAttribute("products", products);
 
        return "products/listProducts";
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getProduct(@PathVariable("id") long id, Model model) {
	    Product product = productDao.findProduct(id);
	    model.addAttribute("product", product);
	 
	    return "products/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String updateProduct(@PathVariable("id") long id, Product product) {
		product.setId(id);
		productDao.updateEntity(product);
	 
	    return "redirect:/products";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteProduct(@PathVariable("id") long id)
	        throws UserDeleteException {
	 
		Product toDelete = productDao.findProduct(id);
	    boolean wasDeleted = productDao.removeEntity(toDelete);
	 
	    if (!wasDeleted) {
	      //  throw new UserDeleteException(toDelete);
	    }
	 
	    // everything OK, see remaining employees
	    return "redirect:/users";
	}
	
	@RequestMapping(params = "new", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String createProductForm(Model model , Principal principal) {    	
    	
    	User loggedInUser;
    	if  (null != principal) {
    		loggedInUser = userDao.findUserByUsername(principal.getName());
    	} else {
    		loggedInUser = null;
    	}
    	
    	model.addAttribute("admin","false");
    	List<GrantedAuthority> authorities = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    	for(GrantedAuthority ga: authorities) {
    		if ((ga.getAuthority()).equalsIgnoreCase("admin")) model.addAttribute("admin","true");
    	}
    	
	    model.addAttribute("product", new Product());
	    return "products/newProduct";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String addProduct(Product product) {
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
