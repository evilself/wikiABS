package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDao<User, Long> implements UserDao {

	
	public boolean removeUser(User user) {
		if(user != null) {			
			if (user.getCreatedArticles().size() > 0) {
				System.out.println("User has associated articles! Cannot delete!");
				return false;
			} else {				
				removeEntity(user);	
				return true;
			}
		}		
        return false;
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
			User u =  (User) userQuery.list().get(0);
			Hibernate.initialize(u.getCreatedArticles());
			Hibernate.initialize(u.getRole());
			Hibernate.initialize(u.getSecurityInfo());
			return u;
		} else {
			return null;
		}
	}
	
	@Override
	public List<User> list() {
		List<User> userList = super.list();
		for(User u: userList){
			Hibernate.initialize(u.getRole());			
		}
		
		 return userList;
	}

	@Override
	public User findUserByUsername(String username) {
		Query userQuery = currentSession().createQuery(
                "from User a where a.userName = :un");
		userQuery.setParameter("un", username); 
		
		if (userQuery.list().size() > 0) {
			return (User) userQuery.list().get(0);
		} else {
			return null;
		}
	}
	
	
	
}
