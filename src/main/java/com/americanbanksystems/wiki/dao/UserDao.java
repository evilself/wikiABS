package com.americanbanksystems.wiki.dao;

import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.service.GenericDAO;

//This class specifies any other services related to User, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface UserDao extends GenericDAO<User, Long>{
	boolean removeUser(User user);
	boolean addUser(User user);
	boolean updateUser(User user);
	User findUser(Long id);
}
