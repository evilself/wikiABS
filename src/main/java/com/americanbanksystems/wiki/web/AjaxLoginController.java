package com.americanbanksystems.wiki.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.User;

@Controller
@RequestMapping("/ajaxLogin")
public class AjaxLoginController {
 
  @Autowired
  @Qualifier("authenticationManager")
  AuthenticationManager authenticationManager;
  
  @Autowired
  UserDao userDao;
 
  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public LoginStatus getStatus() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && !auth.getName().equals("anonymousUser") && auth.isAuthenticated()) {
      return new LoginStatus(true, auth.getName());
    } else {
      return new LoginStatus(false, null);
    }
  } 
 
 
  @RequestMapping(method = RequestMethod.POST)
  public @ResponseBody String login(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
 
	  
	  System.out.println("username "+username);
	  System.out.println("password "+password);
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
    User details = userDao.findUserByUsername(username);//new User(username);
    token.setDetails(details);
    System.out.println("Hit the ajax servlet");
    try {
      Authentication auth = authenticationManager.authenticate(token);
      SecurityContextHolder.getContext().setAuthentication(auth);
      return "Success";
    } catch (BadCredentialsException e) {
      return "Fail";
    }
  }
 
  public class LoginStatus {
 
    private final boolean loggedIn;
    private final String username;
 
    public LoginStatus(boolean loggedIn, String username) {
      this.loggedIn = loggedIn;
      this.username = username;
    }
 
    public boolean isLoggedIn() {
      return loggedIn;
    }
 
    public String getUsername() {
      return username;
    }
  }
}