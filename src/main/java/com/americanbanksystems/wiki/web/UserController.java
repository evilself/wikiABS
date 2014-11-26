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

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.dao.UserRoleDao;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.domain.UserRole;
import com.americanbanksystems.wiki.exception.UserDeleteException;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;
import com.americanbanksystems.wiki.web.helpers.UserUtils;

@Controller
@RequestMapping("/users")
public class UserController implements BaseController {
	
	@Autowired
    private SecurityServiceBean security;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	UserUtils utils;

	//LIST ALL USERS
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
    public String showUsers(Model model) {
		
		//Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	
        List<User> users = userDao.list();
        model.addAttribute("users", users);
 
        return "users/listUsers";
    }
	
	//SHOW DETAILS FOR ONE USER - GET
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String getUser(@PathVariable("id") long id, Model model) {
		
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
		    	
	    User user = userDao.findUser(id);
	    
	    //We dont want to see password characters for editing a user. If we type in a new password then we will change it.
	    //A good practice would be NOT to include the PASSWORD field in our ENTITY, but only on low, database level
	    user.setPassword("");
	    
	    model.addAttribute("user", user);	 
	    return "users/viewUser";
	}
	
	//CHANGES DATA ON A USER - POST
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String updateUser(@PathVariable("id") long id, @Valid @ModelAttribute User user, BindingResult errors, Model model) {	
		
		//This method is validated with JSR303 and Hibernate Validator implementation. In case JavaScript on the front end is disabled -NOT LIKELY
		//@Valid validates the data, BindingResults hold any errors
		if(errors.hasErrors() && !errors.hasFieldErrors("password")) {					
			return "users/viewUser";
		}
		
		User existing = userDao.findUser(id);
		
		try {
			if(!utils.checkUsernameUniqueness(user.getUserName())) {				
				User checkUsernameUser = userDao.findUserByUsername(user.getUserName());
				//We might be dealing with the same user 
				if (existing.getId() != checkUsernameUser.getId()) {
					model.addAttribute("usernameCheck","USERNAME is NOT AVAILABLE!");
					return "users/viewUser";
				}
			}
		} catch (Exception e) {
			logger.error("Username was null or empty while checking for uniqueness!");			
			e.printStackTrace();
		}
				
		user.setId(id);
		
		//If password is not emtpy i.e WE HAVE TYPED IN A NEW PASSWORD
		if(user.getPassword().trim() != "") {			
			try {
				user.setPassword(utils.generateHashedPassword(user.getPassword()));
			} catch (Exception e) {
				logger.error("Password was null or empty while being hashed!");	
				e.printStackTrace();
			}
		} else {
			user.setPassword(existing.getPassword());
		}
				
		UserRole ur = existing.getRole();
		user.setRole(ur);
		ur.setUserName(user.getUserName());
		userDao.updateEntity(user);
		logger.info("User ["+user.getUserName()+"] updated!");
		userRoleDao.updateEntity(ur);	 
	    return "redirect:/users";
	}
	
	//DELETE USER - DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteUser(@PathVariable("id") long id)
	        throws UserDeleteException {
	 
		//If the user has asspcoated articles, WE CANNO DELETE it
		User toDelete = userDao.findUser(id);
		String username = toDelete.getUserName();
	   		
	    if (utils.userDeletable(toDelete)) {
	    	 UserRole role = userRoleDao.findUserRole(toDelete.getRole().getId());			    
			 userRoleDao.removeEntity(role);			    	    
			 boolean wasDeleted = userDao.removeUser(toDelete);			 
		     if (!wasDeleted) {
		    	 //throw new UserDeleteException(toDelete);
		    	 logger.error("User ["+toDelete.getUserName()+"] was not deleted!");
		     }		   
	    } else {
	    	
	    	//TODO implement this
	    	//SHOW info to users
	    	logger.info("User ["+username+"] was deleted!");
	    }
	    // everything OK, see remaining employees
	    return "redirect:/users";
	}
	
	
	//CREATE A NEW USER - GET
	@RequestMapping(params = "new", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String createUserForm(Model model) {
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
		
	    model.addAttribute("user", new User());
	    return "users/newUser";
	}
	
	
	//CREATE A NEW USER - POST.
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')")
	public String addUser(@Valid @ModelAttribute User user, BindingResult errors, Model model) {
		
		//If there are any errors return the form!
		if(errors.hasErrors()) {
			return "users/newUser";
		}		
				
		//Check if another username exists
		try {
			if (utils.checkUsernameUniqueness(user.getUserName())) {
				model.addAttribute("usernameCheck","USERNAME is NOT AVAILABLE!");
				return "users/newUser";
			}
		} catch (Exception e1) {
			logger.error("Username was null or empty while checking for uniqueness!");	
			e1.printStackTrace();
		}
		
		String USER_ROLE = "USER";		
		UserRole role = new UserRole(USER_ROLE,user.getUserName());
		user.setRole(role);
		
		userRoleDao.addEntity(role);		
		
		try {
			user.setPassword(utils.generateHashedPassword(user.getPassword()));
		} catch (Exception e) {
			logger.error("Password was null or empty while hashing!");		
			e.printStackTrace();
		}		
		userDao.addEntity(user);
		logger.info("User ["+user.getUserName()+"] created!");
	    return "redirect:/users";
	}	
	
	//This is not used but left here so i know how it is done next time.
	@ExceptionHandler(UserDeleteException.class)
	public ModelAndView handleDeleteException(UserDeleteException e) {
	    ModelMap model = new ModelMap();
	    model.put("user", e.getUser());
	    return new ModelAndView("users/delete-error", model);
	}	
}
