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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.americanbanksystems.wiki.domain.Article;


@ContextConfiguration(locations = "/persistence-beans.xml")
public class AttachmentDaoTestCase extends DomainAwareBase{
	 
    @Autowired
    private UserDao userDao;
 
    @Autowired
    private ArticleDao articleDao; 
   
 
    @Test
    public void testAdd() {
       /* int size = articleDao.list().size();        
        articleDao.addArticle(new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah"));
      
        // list should have one more employee now
        assertTrue (size < articleDao.list().size());*/
    }
     
    @Test
    public void testUpdate() {
       /* Article article = new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah");
        articleDao.addArticle(article);
        article.setTitle("Hazi");
 
        articleDao.updateArticle(article);
        Article found = articleDao.findArticle(article.getId());
        assertEquals("Hazi", found.getTitle());*/
    }
 
    @Test
    public void testFind() {
    	/*Article article = new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah");
    	articleDao.addArticle(article);
 
    	Article found = articleDao.findArticle(article.getId());
        assertEquals(found, article);*/
    }
 
    @Test
    public void testList() {
      /*  assertEquals(0, articleDao.list().size());
         
        List<Article> articles = Arrays.asList(
                new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah 8"),
                new Article("how to secure Sql Server 2009", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah 9"),
                new Article("how to secure Sql Server 2010", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah 10"));
        for (Article article : articles) {
        	articleDao.addArticle(article);
        }
 
        List<Article> found = articleDao.list();
        assertEquals(3, found.size());
        for (Article article : found) {
            assertTrue(articles.contains(article));
        }*/
    }
 
    @Test
    public void testRemove() {
    	/*Article article = new Article("how to secure Sql Server 2008", "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah");
    	articleDao.addArticle(article);
 
        // successfully added
        assertEquals(article, articleDao.findArticle(article.getId()));
 
        // try to remove
        articleDao.removeArticle(article);
        assertNull(articleDao.findArticle(article.getId()));*/
    }    
}
