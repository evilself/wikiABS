package com.americanbanksystems.wiki.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.americanbanksystems.wiki.domain.User;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class UserDaoTestCase extends DomainAwareBase{
	 
    @Autowired
    private UserDao userDao;
 
    @Autowired
    private ArticleDao articleDao; 
   
 
    @Test
    public void testAdd() {
        int size = userDao.list().size();
        userDao.addUser(new User("boris", "mechkov","bm", "1234"));
 
        // list should have one more employee now
        assertTrue (size < userDao.list().size());
    }
     
    @Test
    public void testUpdate() {
        User user = new User("havka", "lyoteva","hv", "321");
        userDao.addUser(user);
        user.setFirstName("Hazi");
 
        userDao.updateUser(user);
        User found = userDao.findUser(user.getId());
        assertEquals("Hazi", found.getFirstName());
    }
 
    @Test
    public void testFind() {
    	User user = new User("havka", "lyoteva","hv", "321");
    	userDao.addUser(user);
 
    	User found = userDao.findUser(user.getId());
        assertEquals(found, user);
    }
 
    @Test
    public void testList() {
        assertEquals(0, userDao.list().size());
         
        List<User> users = Arrays.asList(
                new User("boris1", "mechkov1","bm", "1234"),
                new User("boris2", "mechkov2","bm", "1234"),
                new User("boris3", "mechkov3","bm", "1234"));
        for (User user : users) {
        	userDao.addUser(user);
        }
 
        List<User> found = userDao.list();
        assertEquals(3, found.size());
        for (User user : found) {
            assertTrue(users.contains(user));
        }
    }
 
    @Test
    public void testRemove() {
    	User user = new User("havka", "lyoteva","hv", "321");
    	userDao.addUser(user);
 
        // successfully added
        assertEquals(user, userDao.findUser(user.getId()));
 
        // try to remove
        userDao.removeUser(user);
        assertNull(userDao.findUser(user.getId()));
    }    
}
