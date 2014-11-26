package com.americanbanksystems.wiki.web;

/**
 * 
 * @Author BorisM
 * @Date 10.25.2014
 * 
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.dao.UserRoleDao;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.domain.UserRole;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;

@Controller
@RequestMapping("/ajaxRegister")
public class AjaxRegisterController {
 
  @Autowired
  @Qualifier("authenticationManager")
  AuthenticationManager authenticationManager;
  
  @Autowired
  UserDao userDao;
  
  @Autowired
  UserRoleDao userRoleDao;
  
  @Autowired
  SecurityServiceBean security;
  
  @RequestMapping(method = RequestMethod.POST)
  public @ResponseBody String login(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("userName") String userName,
                           @RequestParam("password") String password, @RequestParam("newPassword") String newPassword) { 	
	  
	User checkUsernameUser = userDao.findUserByUsername(userName);
	//Check if another username exists
	if (checkUsernameUser != null) {		
		return "Fail(Username exists!)";
	}  
	  
	User user = new User();
	user.setFirstName(firstName);
	user.setLastName(lastName);
	user.setUserName(userName);
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String hashedPassword = passwordEncoder.encode(password);	
	user.setPassword(hashedPassword);
	
    UserRole userRole = new UserRole("USER", userName);
    
    user.setRole(userRole);
    
    userRoleDao.addEntity(userRole);
    
    boolean added = userDao.addEntity(user);
    if(added)    
      return "Success";
    else
      return "Fail";
    
  } 
}