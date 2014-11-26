package com.americanbanksystems.wiki.web;

/**
 * 
 * @Author BorisM
 * @Date 10.25.2014
 * 
 * */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.AttachmentDao;
import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.domain.Attachment;
import com.americanbanksystems.wiki.web.helpers.FileUpload;
 
@Controller
@RequestMapping("/upload")
public class FileUploadController implements BaseController {
	
	@Autowired
    AttachmentDao attDao;
    
    @Autowired
    ArticleDao articleDao;
     
    //Get the upload form	
	@RequestMapping(method = RequestMethod.GET)
    public String displayForm() {
        return "upload/commonsUploadForm";
    }
    
	//Get the upload form, BUT also get the associated article
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String displayExistingForm(@PathVariable("id") Long id, Model model) {    	
    	Article art = articleDao.findArticle(id);
    	model.addAttribute("article", art);
        return "upload/commonsUploadForm";
    }
    
    //NON AJAX METHOD. SAVE THE ATTACHMENTS - POST
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String save(@ModelAttribute("uploadForm") FileUpload uploadForm,
                    Model map) {
         
        MultipartFile file = uploadForm.getFile();
    	
    	Attachment newatt = new Attachment();
    	newatt.setActualFilename(file.getOriginalFilename());
    	try {
			newatt.setAttachment(file.getBytes());
		} catch (IOException e1) {
			logger.error("Attachment ["+newatt.getActualFilename()+"] was corrupt!");
			e1.printStackTrace();
		}
    	newatt.setAttachmentTitle("new attachment");
    	newatt.setAttachmentType("type");
    	
    	attDao.addEntity(newatt);
    	logger.info("Attachment ["+newatt.getActualFilename()+"] added!");    	
    	map.addAttribute("files", newatt);
       
        return "upload/uploadSuccess";
    }
    
    //This returns the attachment
    @RequestMapping("/display/{id}")
    public String download(@PathVariable("id") Long id, HttpServletResponse response) {
         
        Attachment att = attDao.findAttachment(id);
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +att.getActualFilename()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(att.getContentType());
            ByteArrayInputStream bis = new ByteArrayInputStream(att.getAttachment());
            IOUtils.copy(bis, out);
            out.flush();
            out.close();         
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //DELETE AN ATTACHMENT - DELETE
    //RETURN JSON STRING
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody String deleteAttachment(@PathVariable("id") long id) {         
        Attachment att = attDao.findAttachment(id); 
        String fileName = att.getActualFilename();        
        Article art = articleDao.findArticle(att.getArticle().getId());                
        boolean isDeleted = attDao.removeEntity(att);
        logger.info("Attachment ["+fileName+"] deleted!");
        if(!isDeleted) return "Error"; 
        String jsonResult = "{\"identity\":"+att.getId()+", \"count\":"+ (art.getAttachments().size()-1)+"}";
        return jsonResult;
    }
    
    //************************* AJAX METHODS ******************************************************************
    
    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {                
  
      //NOTE, we have used MultipartHttpServletRequest
  
      //get the files from the request object
      Iterator<String> itr =  request.getFileNames();  
      MultipartFile mpf = request.getFile(itr.next());
      
      Attachment newatt = new Attachment();
	  newatt.setActualFilename(mpf.getOriginalFilename());
	  try {
		newatt.setAttachment(mpf.getBytes());
	  } catch (IOException e1) {
		logger.error("Attachment ["+newatt.getActualFilename()+"] was corrupted!");
		e1.printStackTrace();
	  }
	  
	  newatt.setAttachmentTitle("new attachment");
	  newatt.setAttachmentType("type");
	  newatt.setContentType(mpf.getContentType());
	  	
	  //attDao.addEntity(newatt);
	  //SAVES IN MEMORY
	  attDao.saveInMemory(newatt);	  
	  logger.info("Attachment ["+newatt.getActualFilename()+"] was saved successfully!");
      //System.out.println(mpf.getOriginalFilename() +" uploaded!");
   
      return mpf.getOriginalFilename();  
   }
 
    //AJAX UPLOAD TO EXISTING ARTICLE
    //RETURNS JSON
    @RequestMapping(value = "/ajaxUpload/{id}", method = RequestMethod.POST)
    public @ResponseBody String uploadFromEdit(@PathVariable("id") Long id, MultipartHttpServletRequest request, HttpServletResponse response) {                
  
        //Notice, we have used MultipartHttpServletRequest
    	
        Article article = articleDao.findArticle(id);
  
        //1. get the files from the request object
        Iterator<String> itr =  request.getFileNames();  
        MultipartFile mpf = request.getFile(itr.next());      
        Attachment newatt = new Attachment();
	  	newatt.setActualFilename(mpf.getOriginalFilename());
	  	try {
			newatt.setAttachment(mpf.getBytes());
		} catch (IOException e1) {
			logger.error("Attachment ["+newatt.getActualFilename()+"] was corrupted!");
			e1.printStackTrace();
		}
	  	newatt.setAttachmentTitle("new attachment");
	  	newatt.setAttachmentType("type");
	  	newatt.setContentType(mpf.getContentType());
	  	
	  	newatt.setArticle(article);
	  	
	  	attDao.addEntity(newatt);	  	
	  	logger.info("Attachment ["+newatt.getActualFilename()+"] deleted!");
	  	//NOTE!!! Here I am retrieving the same article again, because the size of attachments has changed since i uploaded a new one above
	  	Article newInstane  = articleDao.findArticle(id);	  	 	
	  	
	  	List<Attachment> listAttachment = newInstane.getAttachments();  
	  	
	  	String responseData = " <div class=\"form-group\"> "+
							  " <label class=\"pull-left\" id=\"attachmentCount\" for=\"tag\">Attachments [<span id=\"count\">"+(listAttachment.size())+"</span>]</label>	";
	  	
	  	for(Attachment a: listAttachment) {
	  				responseData +=             " <div id=\""+a.getId()+"\" class=\"col-lg-12 col-sm-12 \" style=\"overflow:auto; margin-bottom:5px;\"> ";			            
	  				responseData +=		        " <div class=\"col-lg-4 text-right\"><label>"+ a.getActualFilename() +"</label></div> ";	  	      		                 
	  				responseData +=		        " <div class=\"col-lg-8 col-sm-8\"> ";
	  				responseData +=		        " <div class=\"col-lg-2 col-sm-2 text-right\"> ";
	  				responseData +=		        " <a target=\"_blank\" class=\"btn btn-info\" style=\"padding-top:1px; padding-bottom: 1px; background-color:#C9C9D5; color:#0066CC; border-color:#C9C9D5\" href=\"/wikiABS/upload/display/"+a.getId()+"\">View</a> ";
	  			    responseData +=		        " </div> ";
	  				responseData +=		        " <div class=\"col-lg-2 col-sm-2 text-left\">	";					                    
	  				responseData +=		        " <button type=\"button\" class=\"btn btn-warning\" style=\"padding-top:1px; padding-bottom: 1px; color:#0066CC; border-color:#C9C9D5\" onclick=\"deleteAttachment("+a.getId()+");\">Delete</button> ";
	  				responseData +=		        " </div> ";						        						               		
	  				responseData +=		        " </div> ";
	  				responseData +=		        " </div>	";	  	           			        		
	    }	  	
      responseData += "</div>";      
      //System.out.println(mpf.getOriginalFilename() +" uploaded!");  
      //System.out.println(responseData);      
      return responseData;  
   }    
}
