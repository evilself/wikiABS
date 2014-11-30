package com.americanbanksystems.wiki.web;

/**
 * 
 * @Author BorisM
 * @Date 10.25.2014
 * 
 * */

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.ProductDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.Product;
import com.americanbanksystems.wiki.web.helpers.EntityGenerator;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;
 
//Using @Controller gives your class access to a lot of useful classes like Security classes, RequestParam, HttpServletRequest, HttpServletResponse etc...DI at its best!
@Controller
@RequestMapping(value={"/", "/welcome"})
public class WelcomeController implements BaseController {	
	
	@Autowired
    private EntityGenerator entityGenerator;
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
    private ProductDao productDao;
	
	@Autowired
    private ArticleDao articleDao;
	
	@Autowired
    private SecurityServiceBean security;
 
	final private int ARTICLE_SIZE = 5;
	 
    @RequestMapping(method = RequestMethod.GET)
    public String showMenu(Model model) {
    	
    	//Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	
    	//Here i am getting the latest 5 articles for each product to display.
    	List<Product> prodList = productDao.list();    	
    	for(Product prod : prodList) {
    		List<Article> articles = articleDao.findArticleByProduct(prod, ARTICLE_SIZE);
            model.addAttribute((prod.getProductIdentity()+"_Articles"), articles);    		
    	}   	
        
    	//Before Spring 3, we had to send view and model from a controller, but now only view, which resolved to a .jsp file.
        return "index";
    }
    
    
    //This web method is invoked when we request a restricted resource and it redirects to a login page. NOT an AJAX login though.
    //If we pass an optional 'error' URL parameter then the login attempt has failed and prints an error message
    @RequestMapping(value="/loginFull")
    public String loginFull(HttpServletRequest request, Model model, @RequestParam(value = "error", required = false) String error,
    		@RequestParam(value = "logout", required = false) String logout){
    	
    		//Set the error message in the model
	    	if (error != null) {
				model.addAttribute("error", "Invalid username and password!");
			}    	
    	
        return "loginFull";
    }
    
    //This is invoked with AJAX login! 
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, Model model){
        return "login";
    }
    
    //This is invoked with AJAX register!
    @RequestMapping(value="/register")
    public String register(HttpServletRequest request, Model model){
        return "register";
    } 
     
    //Custom Logout method. I am using this instead of /j_spring_security_logout
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
    	//I dont actually have to invalidate here, because i changed this bean to resolve security data dynamically! Had problems with storing security data when session expires
    	security.invalidate();
    	
    	//To manually logout use these two Handlers-Remember_Me option and context as well.Note in the logout() methods the'null' is the Authentication object.
    	CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
	    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
	    cookieClearingLogoutHandler.logout(request, response, null);
	    securityContextLogoutHandler.logout(request, response, null);
        return "redirect:/";
    }
     
    //This is invoked when authorization fails
    @RequestMapping(value="/denied")
    public String denied(){
        return "denied";
    }
    
   //This is invoked when ABOUT page is requested
    @RequestMapping(value="/about")
    public String about(){
        return "about";
    }
    
    //I used this PostConstruct to insert some test data initially.
    @PostConstruct
    public void prepareFakeDomain() {
      //entityGenerator.deleteDomain();
      //entityGenerator.generateDomain();
    }
}