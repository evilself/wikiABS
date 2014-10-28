package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.20.2014
 * 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.domain.UserRole;

import static org.mockito.Mockito.*;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class UserDaoTestCase extends DomainAwareBase{
	 
    @Autowired
    private UserDao userDao;
 
    @Autowired
    private ArticleDao articleDao; 
    
    @Autowired
    private UserRoleDao userRoleDao;
    
    private UserRole adminRole;
    
	@Before
    public void setup() {       
        UserRole adminRole = new UserRole("ADMIN");
        userRoleDao.addEntity(adminRole);    
        setAdminRole(adminRole);       
        
        UserRole mockedRole = mock(UserRole.class);
    }
   
 
    @Test
    public void testAdd() {
        int size = userDao.list().size();       
        User u = new User("Boris", "Mechkov", "bm", "12345", getAdminRole());
        userDao.addEntity(u);
 
        // list should have one more employee now
        assertTrue (size < userDao.list().size());
        
    }
     
    @Test
    public void testUpdate() {    	
        User user = new User("Boris", "Mechkov", "bm", "12345", getAdminRole());
        userDao.addEntity(user);
        user.setFirstName("Bo");
 
        userDao.updateEntity(user);
        User found = userDao.findUser(user.getId());
        assertEquals("Bo", found.getFirstName());
    }
 
    @Test
    public void testFind() {
    	User user = new User("Boris", "Mechkov", "bm", "12345", getAdminRole());
    	userDao.addEntity(user);
 
    	User found = userDao.findUser(user.getId());
        assertEquals(found, user);
    }
 
    @Test
    public void testList() {
        assertEquals(0, userDao.list().size());
         
        List<User> users = Arrays.asList(
        		new User("Boris1", "Mechkov1", "bm", "12345", getAdminRole()),
        		new User("Boris2", "Mechkov2", "bm", "12345", getAdminRole()),
        		new User("Boris3", "Mechkov3", "bm", "12345", getAdminRole()));
        for (User user : users) {
        	userDao.addEntity(user);
        }
 
        List<User> found = userDao.list();
        assertEquals(3, found.size());
        for (User user : found) {
            assertTrue(users.contains(user));
        }
    }
 
    @Test
    public void testRemove() {
    	User user = new User("Boris1", "Mechkov1", "bm", "12345", getAdminRole());
    	userDao.addEntity(user);
 
        // successfully added
        assertEquals(user, userDao.findUser(user.getId()));
 
        // try to remove
        userDao.removeEntity(user);
        assertNull(userDao.findUser(user.getId()));
    }
    
   
    
    public UserRole getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(UserRole adminRole) {
		this.adminRole = adminRole;
	}
}
