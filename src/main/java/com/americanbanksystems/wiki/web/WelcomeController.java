package com.americanbanksystems.wiki.web;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.web.helpers.EntityGenerator;
 
@Controller
@RequestMapping("/")
public class WelcomeController {
	
	@Autowired
    private EntityGenerator entityGenerator;
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
    private ArticleDao articleDao;
 
 
    @RequestMapping(method = RequestMethod.GET)
    public String showMenu(Model model) {
    	
    	List<User> users = userDao.list();
        model.addAttribute("users", users);
        model.addAttribute("searchCriteria","");
    	
        model.addAttribute("today", new Date());
        return "index";
    }
    
    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, Model model){
        return "login";
    }
     
    @RequestMapping(value="/logout")
    public String logout(){
        return "logout";
    }
     
    @RequestMapping(value="/denied")
    public String denied(){
        return "denied";
    }
    
    @PostConstruct
    public void prepareFakeDomain() {
        entityGenerator.deleteDomain();
        entityGenerator.generateDomain();
    }
}