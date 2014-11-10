package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to User, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface UserDao extends GenericDAO<User, Long> {	
	User findUser(Long id);
	User findUserByUsername(String username);
	boolean removeUser(User user); 
}
