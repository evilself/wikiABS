package com.americanbanksystems.wiki.web;

/**
 * 
 * @Author BorisM
 * @Date 10.25.2014
 * 
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.AttachmentDao;
import com.americanbanksystems.wiki.dao.ProductDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.Attachment;
import com.americanbanksystems.wiki.domain.Product;
import com.americanbanksystems.wiki.domain.User;
import com.americanbanksystems.wiki.exception.ArticleDeleteException;
import com.americanbanksystems.wiki.web.helpers.RichTextFilter;
import com.americanbanksystems.wiki.web.helpers.SecurityServiceBean;

@Controller
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
    private SecurityServiceBean security;	
	
	@Autowired
	RichTextFilter filter;
	
	//GET ALL ARTICLES - GET
	@RequestMapping(method = RequestMethod.GET)
    public String showArticles(Model model) {
    	
    	//Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	
        List<Article> articles = articleDao.findArticlesByTag(""); //change this later on TODO FIX ME!
        model.addAttribute("articles", articles);
 
        return "articles/listArticles";
    }
	
	//
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getArticle(@PathVariable("id") long id, Model model) {
		
		//Security information
		model.addAttribute("admin",security.isAdmin()); 
		model.addAttribute("loggedUser", security.getLoggedInUser());
		
	    Article article = articleDao.findArticle(id);
	    model.addAttribute("art", article);
	    
	    //Editable
	    if(null != security.getLoggedInUser()) {
		    if(security.getLoggedInUser().getUserName().equalsIgnoreCase(article.getCreatedByUser().getUserName()) || security.isAdmin())
		    	model.addAttribute("editable", true );
		    else model.addAttribute("editable", false );
	    } else model.addAttribute("editable", false );
	   	//Hibernate.initialize(article.getAttachments());
	    List<Attachment> listAttachment = article.getAttachments();
	    //System.out.println(listAttachment.size()+" is what i retrieved");
	    
	    //List<String> fileNames = new ArrayList<String>();
	    List<HashMap<String, String>> atts = new ArrayList<HashMap<String, String>>();
	    	    
	    for(Attachment a: listAttachment) {
	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("name", a.getActualFilename());
	    	map.put("id", ""+a.getId());
	    	atts.add(map);
	    }
	    
	    model.addAttribute("attachments", atts);	 
	    return "articles/viewArticle";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editArticle(@PathVariable("id") long id, Model model) {
		
		//Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());		
		
	    Article article = articleDao.findArticle(id);
	    model.addAttribute("article", article);
	    
	    if(!article.getCreatedByUser().getUserName().equalsIgnoreCase(security.getLoggedInUser().getUserName()) && !security.isAdmin()) return "/denied";
	    
	    
	    model.addAttribute("products", productDao.list());
	    
	   	//Hibernate.initialize(article.getAttachments());
	    List<Attachment> listAttachment = article.getAttachments();
	    //System.out.println(listAttachment.size()+" is what i retrieved");
	    
	    //List<String> fileNames = new ArrayList<String>();
	    List<HashMap<String, String>> atts = new ArrayList<HashMap<String, String>>();
	    	    
	    for(Attachment a: listAttachment) {
	    	HashMap<String, String> map = new HashMap<String, String>();
	    	map.put("name", a.getActualFilename());
	    	map.put("id", ""+a.getId());
	    	atts.add(map);
	    }
	    
	    model.addAttribute("attachments", atts);
	    model.addAttribute("action", "../edit/"+id);
	    model.addAttribute("upload", "/wikiABS/upload/"+article.getId());
	 
	    return "articles/createArticle";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String updateArticle(@PathVariable("id") long id, Article article) {
		
		//Security information
    	//model.addAttribute("admin",security.isAdmin()); 
    	//model.addAttribute("loggedUser", security.getLoggedInUser());
		
		article.setId(id);
		
		Article existing = articleDao.findArticle(id);
		User createdByUser = existing.getCreatedByUser();
		
		List<Attachment> attachmentListToBeSaved = attachmentDao.getSavedAttachments();
		//System.out.println(attachmentListToBeSaved.size() + "  is the att list in CONTROLLER");
		
		Product prod = productDao.findProduct(Long.parseLong(article.getProduct().getProductName()));
		article.setProduct(prod);
				
		article.setCreatedByUser(createdByUser);		
		//article.setModifiedByUser(security.getLoggedInUser());
		articleDao.updateEntity(article);
		
		for(Attachment att:attachmentListToBeSaved) {
			
			att.setArticle(article);
			attachmentDao.addEntity(att);
			
		}
		
		attachmentListToBeSaved.clear();
	  		
	 	
	    return "redirect:/articles";
	}
	
	//DELETE ARTICLE
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
	    } else {
	    	//return "Article deleted successfully!";
	    }	    
	    // everything OK, see remaining employees
	    return "redirect:/articles";
	}
	
	@RequestMapping(params = "new", method = RequestMethod.GET)
	public String createArticleForm(Model model) {
		
		//Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
    	model.addAttribute("action", "articles");
    	model.addAttribute("upload", "/wikiABS/upload");
		
    	model.addAttribute("products", productDao.list());
    	
	    model.addAttribute("article", new Article());
	    attachmentDao.clearMemoryStore();
	    return "articles/createArticle";
	}
	
	@RequestMapping(params = "searchCriteria", method = RequestMethod.GET)
	public String getArticlesFromSearch(@RequestParam("searchCriteria")String tag, Model model) {
		
		//Security information
    	model.addAttribute("admin",security.isAdmin()); 
    	model.addAttribute("loggedUser", security.getLoggedInUser());
		
		 List<Article> articles = articleDao.findArticlesByTagOrTitle(tag);
	        model.addAttribute("articles", articles);
	        System.out.println("searchCriteriea");
	        return "articles/listArticles";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String addArticle(@Valid @ModelAttribute Article article, BindingResult errors, Model model) {
		if(errors.hasErrors()) {
			//Security information
	    	model.addAttribute("admin",security.isAdmin()); 
	    	model.addAttribute("loggedUser", security.getLoggedInUser());
	    	model.addAttribute("action", "articles");
	    	model.addAttribute("upload", "/wikiABS/upload");
			
	    	model.addAttribute("products", productDao.list());
			return "articles/createArticle";
		}
		//Security information
    	//model.addAttribute("admin",security.isAdmin()); 
    	//model.addAttribute("loggedUser", security.getLoggedInUser());
		
		String desc = article.getDescription();
		article.setDescription(filter.filter(desc));
		
		List<Attachment> attachmentListToBeSaved = attachmentDao.getSavedAttachments();
		//System.out.println(attachmentListToBeSaved.size() + "  is the att list in CONTROLLER");
		
		Product prod = productDao.findProduct(Long.parseLong(article.getProduct().getProductName()));
		article.setProduct(prod);
				
		article.setCreatedByUser(security.getLoggedInUser());		
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
