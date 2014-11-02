package com.americanbanksystems.wiki.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.AttachmentDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.Attachment;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.exception.ArticleDeleteException;

@Controller
@RequestMapping("/articles")
public class ArticleController {
	
	private ArticleDao articleDao;
	
	private UserDao userDao;
	
	private AttachmentDao attachmentDao;
	
	
	@Autowired
	public void setattachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setArticleDao(ArticleDao articleDao) {
	    this.articleDao = articleDao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public String showArticles(Model model, Principal principal) {
    	
    	List<User> users = userDao.list();
    	User loggedInUser;
    	if  (null != principal) {
    		loggedInUser = userDao.findUserByUsername(principal.getName());
    	} else {
    		loggedInUser = null;
    	}
    	model.addAttribute("loggedUser", loggedInUser);
        List<Article> articles = articleDao.list();
        model.addAttribute("articles", articles);
 
        return "articles/list";
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getArticle(@PathVariable("id") long id, Model model) {
	    Article article = articleDao.findArticle(id);
	    model.addAttribute("art", article);
	    
	   	//Hibernate.initialize(article.getAttachments());
	    List<Attachment> listAttachment = article.getAttachments();
	    System.out.println(listAttachment.size()+" is what i retrieved");
	    
	    List<String> fileNames = new ArrayList<String>();
	    
	    for(Attachment a: listAttachment) {
	    	fileNames.add(a.getActualFilename());
	    }
	    
	    model.addAttribute("attachments", fileNames);
	 
	    return "articles/view";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String updateArticle(@PathVariable("id") long id, Article article) {
		article.setId(id);
		articleDao.updateEntity(article);
	 	
	    return "redirect:/articles";
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteArticle(@PathVariable("id") long id)
	        throws ArticleDeleteException {
	 
	    Article toDelete = articleDao.findArticle(id);
	    
	    List<Attachment> listAttachment = toDelete.getAttachments();
	    	    
	    for(Attachment a: listAttachment) {
	    	attachmentDao.removeEntity(a);
	    }
	    
	    boolean wasDeleted = articleDao.removeEntity(toDelete);
	 
	    if (!wasDeleted) {
	        throw new ArticleDeleteException(toDelete);
	    }
	 
	    // everything OK, see remaining employees
	    return "redirect:/articles";
	}
	
	@RequestMapping(params = "new", method = RequestMethod.GET)
	public String createArticleForm(Model model) {
	    model.addAttribute("article", new Article());
	    return "articles/createArticle";
	}
	
	@RequestMapping(params = "searchCriteria", method = RequestMethod.GET)
	public String getArticlesFromSearch(@RequestParam("searchCriteria")String tag, Model model) {
		 List<Article> articles = articleDao.findArticlesByTag(tag);
	        model.addAttribute("articles", articles);
	        System.out.println("searchCriteriea");
	        return "articles/list";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addArticle(Article article, Principal principal) {
		
		String username = principal.getName();
		System.out.println(username);	
		
		List<Attachment> attachmentListToBeSaved = attachmentDao.getSavedAttachments();
		System.out.println(attachmentListToBeSaved.size() + "  is the att list in CONTROLLER");
		
		User loggedInUser = userDao.findUserByUsername(username);
		article.setCreatedByUser(loggedInUser);		
		articleDao.addEntity(article);
		
		for(Attachment att:attachmentListToBeSaved) {
			
			att.setArticle(article);
			attachmentDao.addEntity(att);
			
		}
		
		attachmentListToBeSaved.clear();
	 
	    return "redirect:/articles";
	}
	
	@ExceptionHandler(ArticleDeleteException.class)
	public ModelAndView handleDeleteException(ArticleDeleteException e) {
	    ModelMap model = new ModelMap();
	    model.put("article", e.getArticle());
	    return new ModelAndView("articles/delete-error", model);
	}

}
