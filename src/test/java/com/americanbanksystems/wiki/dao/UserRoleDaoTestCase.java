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

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.americanbanksystems.wiki.domain.UserRole;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class UserRoleDaoTestCase extends DomainAwareBase{
	 
    @Autowired
    private UserRoleDao userRoleDao;
     
    @Test
    public void testAdd() {
        int size = userRoleDao.list().size();
        userRoleDao.addEntity(new UserRole("MANAGER"));
 
        // list should have one more user role now
        assertTrue (size < userRoleDao.list().size());
    }
     
    @Test
    public void testUpdate() {
        UserRole role = new UserRole("MANAGER");
        userRoleDao.addEntity(role);
        role.setRole("OFFICER");
 
        userRoleDao.updateEntity(role);
        UserRole found = userRoleDao.findUserRole(role.getId());
        assertEquals("OFFICER", found.getRole());
    }
 
    @Test
    public void testFind() {
    	UserRole role = new UserRole("MANAGER");
        userRoleDao.addEntity(role);
 
        UserRole found = userRoleDao.findUserRole(role.getId());
        assertEquals(found, role);
    }
 
    @Test
    public void testList() {
        assertEquals(0, userRoleDao.list().size());
         
        List<UserRole> userRoles = Arrays.asList(
        		new UserRole("MANAGER"),        		
        		new UserRole("USER"));
        for (UserRole role : userRoles) {
        	userRoleDao.addEntity(role);
        }
 
        List<UserRole> found = userRoleDao.list();
        assertEquals(2, found.size());
        for (UserRole role : found) {
            assertTrue(userRoles.contains(role));
        }
    }
 
    @Test
    public void testRemove() {
    	UserRole role = new UserRole("MANAGER");
        userRoleDao.addEntity(role);
 
        // successfully added
        assertEquals(role, userRoleDao.findUserRole(role.getId()));
 
        // try to remove
        userRoleDao.removeEntity(role);
        assertNull(userRoleDao.findUserRole(role.getId()));
    }  
}
