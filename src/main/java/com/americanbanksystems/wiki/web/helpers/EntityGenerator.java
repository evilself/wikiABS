package com.americanbanksystems.wiki.web.helpers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.dao.UserRoleDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.domain.UserRole;
import com.americanbanksystems.wiki.service.GenericDAO;
 
/**
 * Small util helper for generating entities to simulate real system.
 */
@Service //NOTE SERVICE here
public final class EntityGenerator {
 
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserRoleDao userRoleDao;
 
    @Autowired
    private ArticleDao articleDao;
  
    public void generateDomain() {
    	
    	UserRole admin = new UserRole("ADMIN");
    	UserRole user = new UserRole("USER");
    	
        User steve = new User("Steve", "Walley", "sw", "matrix", user);
        User kenny = new User("Kenny", "Rajiah", "kr", "maurit", admin);
        User boris = new User("Boris", "Mechkov", "bm", "sirob", admin);
                        
        // free managers
        //Article itArticle = new Article("how to do IT", "it stuff stuff stuff stuff");
        //Article sportArticle = new Article("Arsenal champions", "Arsenal Arsenal Coyg Arsenal Arsenal Coyg Arsenal Arsenal Coyg");
 
        admin.setUserName("bm");
        user.setUserName("sw");
                
        addAll(userRoleDao, admin, user);
        addAll(userDao, steve, kenny, boris);
       // addAll(articleDao, itArticle, sportArticle);
       
    }
     
    public void deleteDomain() {   	
    	
        List<Article> articleList = articleDao.list();
        for (Article article : articleList) {
            articleDao.removeEntity(article);
        }
 
        List<User> userList = userDao.list();
        for (User user : userList) {
        	userDao.removeEntity(user);
        }
        
        List<UserRole> userRoleList = userRoleDao.list();
        for (UserRole role : userRoleList) {
        	userRoleDao.removeEntity(role);
        }
    }
     
    private <T> void addAll(GenericDAO<T, Long> dao, T... entites) {
        for (T o : entites) {
            dao.addEntity(o);
        }
    }
}