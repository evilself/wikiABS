package com.americanbanksystems.wiki.web.helpers;

/**
 * 
 * @Author BorisM
 * @Date 11.24.2014
 * 
 * */


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.SecurityInfoDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.dao.UserRoleDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.SecurityInfo;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.domain.UserRole;

//User-centric utility methods
@Service
public class UserUtils {
	static final Logger logger = Logger.getLogger(UserUtils.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private SecurityInfoDao securityInfoDao;
	
	//This method returns a hashed password for a User
	public String generateHashedPassword(String password) throws Exception {		
		if (password != null && password != "") {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			return passwordEncoder.encode(password);			
		} else {
			logger.error("Password was null or empty while being hashed!");
			throw new Exception("Password should not be null nor empty here!");
		}
		
		// BCRYPT
		// bcrypt is a key derivation function for passwords designed by Niels Provos and David Mazières, based on the Blowfish cipher, and presented at USENIX in 1999.[1] 
		// Besides incorporating a salt to protect against rainbow table attacks, bcrypt is an adaptive function: over time, the iteration count can be increased to make it slower, 
		// so it remains resistant to brute-force search attacks even with increasing computation power.
		// The bcrypt function is the default password hash algorithm for BSD and many other systems
	}
	
	//This method check to see if we have an existing user with the provided username
	public boolean checkUsernameUniqueness(String username) throws Exception {		
		User checkUsernameUser = userDao.findUserByUsername(username);		
		//Check if another username exists
		if (checkUsernameUser != null) {
			return false;
		}	
		return true;
	}
	
	//Check if the user is deletable i.e does not have any associated articles
	public boolean userDeletable(User user) {
		List<Article> createdArticles = articleDao.findArticleByCreator(user);		
	    if (createdArticles.size() > 0) {
	    	return false;		   
	    } else {
	    	return true;
	    }
	}
	
	//********************************** THESE METHODS ARE USED IN REGISTRATION WEB FLOW******************************
	
	//Move this to a User account service
    public String checkUsernameAvailability(User user) {
    
    	System.out.println(user.getUserName() + " is the username in web flow");
    	try {
			if(checkUsernameUniqueness(user.getUserName())) return "success";
			else return "failure";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	return "failure";      
    }
    
    //Move this to a User account service
    public String hashUserPassword(User user) {
    
    	if(user != null) {    		
    		try {
				user.setPassword(generateHashedPassword(user.getPassword().trim()));
				return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    		
    	}
    	
    	return "failure";      
    }
    
  //Move this to a User account service
    public String registerUser(User user, SecurityInfo securityInfo) {
    
    	if(user != null && securityInfo != null) {   
    		System.out.println("GOOD DATA");  

    		String USER_ROLE = "USER";		
    		UserRole role = new UserRole(USER_ROLE,user.getUserName());
    		user.setRole(role);    		
    		userRoleDao.addEntity(role);
    		
    		//userDao.addEntity(user);
    		
    		securityInfo.setUsername(user.getUserName().trim());
    		securityInfoDao.addEntity(securityInfo); 
    		user.setSecurityInfo(securityInfo);
    		
    		userDao.addEntity(user);
    		
    		return "success";
    	}
    	
    	return "failure";      
    }
}
