package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import com.americanbanksystems.wiki.domain.UserRole;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to UserRole, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface UserRoleDao extends GenericDAO<UserRole, Long> {	
	UserRole findUserRole(Long id);
}
