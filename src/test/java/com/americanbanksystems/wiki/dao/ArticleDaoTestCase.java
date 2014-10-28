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

import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.domain.UserRole;


@ContextConfiguration(locations = "/persistence-beans.xml")
public class ArticleDaoTestCase extends DomainAwareBase{
	 
    @Autowired
    private UserDao userDao;
 
    @Autowired
    private ArticleDao articleDao; 
   
 
    @Autowired
    private UserRoleDao userRoleDao;
    
    private UserRole adminRole;

	private User admin;
    
    @Before
    public void setup() {
    	UserRole adminRole = new UserRole("ADMIN");
        userRoleDao.addEntity(adminRole);    
        setAdminRole(adminRole);
        
        User admin = new User("Boris", "Mechkov", "bm", "12345", getAdminRole());
        userDao.addEntity(admin);
        setAdmin(admin);    	
    }
    
    @Test
    public void testAdd() {
        int size = articleDao.list().size();        
        articleDao.addEntity(new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah", getAdmin()));
      
        // list should have one more employee now
        assertTrue (size < articleDao.list().size());
    }
     
    @Test
    public void testUpdate() {
        Article article = new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah", getAdmin());
        articleDao.addEntity(article);
        article.setTitle("Hazi");
 
        articleDao.updateEntity(article);
        Article found = articleDao.findArticle(article.getId());
        assertEquals("Hazi", found.getTitle());
    }
 
    @Test
    public void testFind() {
    	Article article = new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah", getAdmin());
    	articleDao.addEntity(article);
 
    	Article found = articleDao.findArticle(article.getId());
        assertEquals(found, article);
    }
 
    @Test
    public void testList() {
        assertEquals(0, articleDao.list().size());
         
        List<Article> articles = Arrays.asList(
                new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah 8", getAdmin()),
                new Article("how to secure Sql Server 2009", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah 9", getAdmin()),
                new Article("how to secure Sql Server 2010", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah 10", getAdmin()));
        for (Article article : articles) {
        	articleDao.addEntity(article);
        }
 
        List<Article> found = articleDao.list();
        assertEquals(3, found.size());
        for (Article article : found) {
            assertTrue(articles.contains(article));
        }
    }
 
    @Test
    public void testRemove() {
    	Article article = new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah", getAdmin());
    	articleDao.addEntity(article);
 
        // successfully added
        assertEquals(article, articleDao.findArticle(article.getId()));
 
        // try to remove
        articleDao.removeEntity(article);
        assertNull(articleDao.findArticle(article.getId()));
    }   
    
    public UserRole getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(UserRole adminRole) {
		this.adminRole = adminRole;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}
}
