package com.americanbanksystems.wiki.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.dao.UserRoleDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.domain.UserRole;
import com.americanbanksystems.wiki.exception.UserDeleteException;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private SecurityServiceBean security;
	
	private ArticleDao articleDao;
	
	private UserRoleDao userRoleDao;
	
	@Autowired
	public void setUserRoleDao(UserRoleDao userRoleDao) {
	    this.userRoleDao = userRoleDao;
	}
	
	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
	    this.articleDao = articleDao;
	}
	
	private UserDao userDao;
	 
	@Autowired
	public void setUserDao(UserDao userDao) {
	    this.userDao = userDao;
	}

	@RequestMapping(method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<User> users = userDao.list();    
        
        //Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	
        model.addAttribute("users", users);
 
        return "users/listUsers";
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String getUser(@PathVariable("id") long id, Model model) {
		
		
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
		    	
	    User user = userDao.findUser(id);
	    model.addAttribute("user", user);
	 
	    return "users/viewUser";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String updateEmployee(@PathVariable("id") long id, User user) {
		user.setId(id);
		
		User existing = userDao.findUser(id);
		
		String password = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		
		user.setRole(existing.getRole());
		
		user.setPassword(hashedPassword);
		
		userDao.updateEntity(user);
	 
	    return "redirect:/users";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteUser(@PathVariable("id") long id)
	        throws UserDeleteException {
	 
		User toDelete = userDao.findUser(id);
	    List<Article> createdArticles = articleDao.findArticleByCreator(toDelete);
		
	    if (createdArticles.size() > 0) {
	    	System.out.println("User cannot be deleted, because it has associated articles!");
		   
	    } else {
	    	
	    	 UserRole role = userRoleDao.findUserRole(toDelete.getRole().getId());
			    
		    userRoleDao.removeEntity(role);
		    	    
		    boolean wasDeleted = userDao.removeUser(toDelete);
		 
		    if (!wasDeleted) {
		    //    throw new UserDeleteException(toDelete);
		    }
	    }
	    // everything OK, see remaining employees
	    return "redirect:/users";
	}
	
	@RequestMapping(params = "new", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String createUserForm(Model model) {
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
		
	    model.addAttribute("user", new User());
	    return "users/newUser";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String addUser(User user) {
		
		UserRole role = new UserRole("USER",user.getUserName());
		user.setRole(role);
		
		userRoleDao.addEntity(role);
		
		String password = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		user.setPassword(hashedPassword);
		
		userDao.addEntity(user);
	 
	    return "redirect:/users";
	}
	
	
	@ExceptionHandler(UserDeleteException.class)
	public ModelAndView handleDeleteException(UserDeleteException e) {
	    ModelMap model = new ModelMap();
	    model.put("user", e.getUser());
	    return new ModelAndView("users/delete-error", model);
	}
	
}
