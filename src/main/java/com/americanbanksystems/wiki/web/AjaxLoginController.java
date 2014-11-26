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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;

@Controller
@RequestMapping("/ajaxLogin")
public class AjaxLoginController {
 
  @Autowired
  @Qualifier("authenticationManager")
  AuthenticationManager authenticationManager;
  
  @Autowired
  UserDao userDao;
  
  @Autowired
  SecurityServiceBean security;
  
  @RequestMapping(method = RequestMethod.POST)
  public @ResponseBody String login(@RequestParam("username") String username, @RequestParam("password") String password) { 	  
	
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
    User details = userDao.findUserByUsername(username);
    token.setDetails(details);
   
    try {
      Authentication auth = authenticationManager.authenticate(token);
      SecurityContextHolder.getContext().setAuthentication(auth);
      security.setup();
      return "Success";
    } catch (BadCredentialsException e) {
      return "Fail";
    }
  } 
}