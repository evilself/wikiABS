package com.americanbanksystems.wiki.web.helpers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.dao.UserRoleDao;
import com.americanbanksystems.wiki.domain.User;
 
/**
 * This is going to be used to hold security info for the whole application
 */
@Service
public final class SecurityServiceBean {
 
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserRoleDao userRoleDao;
    
    private boolean isAdmin = false;
    
    public boolean isAdmin() {
		return isAdmin;
	}

	private void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}	

	//Spring Authorities / loggedIn user roles
    private List<GrantedAuthority> authorities;
    
    public List<GrantedAuthority> getAuthorities() {
    	return (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();		
	}
    
    private void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	//Hold the logged in user
    private User loggedInUser;
   
    public User getLoggedInUser() {
		return loggedInUser;
	}

	private void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	
	public void setup() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		getLoggedInUserFromPrincipal(principal);
		for(GrantedAuthority ga: getAuthorities()) {
    		if ((ga.getAuthority()).equalsIgnoreCase("admin")) {
    			setAdmin(true);
    			break;
    		}    		
    	}
	}
	
	public void invalidate() {
		setAdmin(false);
		setLoggedInUser(null);
		setAuthorities(null);
	}

	private void getLoggedInUserFromPrincipal(Object principal) {
		User loggedInUser;
		String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
    	if  (null != principal) {
    		loggedInUser = userDao.findUserByUsername(username);
    	} else {
    		loggedInUser = null;
    	}
		setLoggedInUser(loggedInUser);
	} 
}