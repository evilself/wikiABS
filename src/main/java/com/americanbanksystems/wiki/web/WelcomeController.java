package com.americanbanksystems.wiki.web;


import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.ProductDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.Product;
import com.americanbanksystems.wiki.web.helpers.EntityGenerator;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;
 
@Controller
@RequestMapping(value={"/", "/welcome"})
public class WelcomeController {
	
	private static final Logger logger = Logger.getLogger(WelcomeController.class);
	
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
 
 
    @RequestMapping(method = RequestMethod.GET)
    public String showMenu(Model model) {   	
    	if(logger.isDebugEnabled()){
			logger.debug("getWelcome is executed!");
		}
    	
    	//Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	
    	List<Product> prodList = productDao.list();
    	int size = 5;
    	for(Product prod : prodList) {
    		List<Article> articles = articleDao.findArticleByProduct(prod, size);
            model.addAttribute((prod.getProductIdentity()+"_Articles"), articles);    		
    	}
    	
    	//List<Article> cproArticles = articleDao.findArticleByProduct(product);
        //model.addAttribute("cproArticles", cproArticles);
        
        //List<Article> eliteArticles = articleDao.list();
        //model.addAttribute("eliteArticles", eliteArticles);
        
        //List<Article> cplArticles = articleDao.list();
        //model.addAttribute("cplArticles", cplArticles);       
        
        model.addAttribute("searchCriteria","");
    	
        model.addAttribute("today", new Date());
        return "index";
    }
    
    @RequestMapping(value="/loginFull")
    public String loginFull(HttpServletRequest request, Model model){
        return "loginFull";
    }
    
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, Model model){
        return "login";
    }
    
    @RequestMapping(value="/register")
    public String register(HttpServletRequest request, Model model){
        return "register";
    } 
     
    //Custom Logout method. I am using this instead of j_spring_logout
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
    	security.invalidate();
    	CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
	    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
	    cookieClearingLogoutHandler.logout(request, response, null);
	    securityContextLogoutHandler.logout(request, response, null);
        return "redirect:/";
    }
     
    @RequestMapping(value="/denied")
    public String denied(){
        return "denied";
    }
    
    @PostConstruct
    public void prepareFakeDomain() {
      //  entityGenerator.deleteDomain();
      //entityGenerator.generateDomain();
    }
}