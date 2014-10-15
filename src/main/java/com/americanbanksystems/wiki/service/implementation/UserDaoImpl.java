package com.americanbanksystems.wiki.service.implementation;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDao<User, Long> implements UserDao {

	
	public boolean removeUser(User user) {
		
		removeEntity(user);
        return true;
	}
	
	public boolean addUser(User user) {
		
		addEntity(user);
		return true;
	}
	
	public boolean updateUser(User user) {
		
		updateEntity(user);
		return true;
	}
	
	public User findUser(Long id) {
		Query userQuery = currentSession().createQuery(
                "from User a where a.id = :urs");
		userQuery.setParameter("urs", id); 
		
		if (userQuery.list().size() > 0) {
			return (User) userQuery.list().get(0);
		} else {
			return null;
		}
	}
	
}
