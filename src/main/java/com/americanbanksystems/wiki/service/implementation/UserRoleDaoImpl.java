package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.UserRoleDao;
import com.americanbanksystems.wiki.domain.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends HibernateDao<UserRole, Long> implements UserRoleDao {
	
	public boolean removeUserRole(UserRole role) {
		//todo fix this
		return false;
	}
	
	public boolean addUserRole(UserRole role) {		
		addEntity(role);
		return true;
	}
	
	public boolean updateUserRole(UserRole role) {		
		updateEntity(role);
		return true;
	}
	
	public UserRole findUserRole(Long id) {
		Query userRoleQuery = currentSession().createQuery(
                "from UserRole ur where ur.id = :urs");
		userRoleQuery.setParameter("urs", id); 
		
		if (userRoleQuery.list().size() > 0) {
			return (UserRole) userRoleQuery.list().get(0);
		} else {
			return null;
		}
	}
	
}
