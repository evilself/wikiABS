package com.americanbanksystems.wiki.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FileUploadController {
     
    @RequestMapping(method = RequestMethod.GET)
    public String displayForm() {
        return "upload/commonsUploadForm";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String displayExistingForm(@PathVariable("id") Long id, Model model) {
    	
    	Article art = articleDao.findArticle(id);
    	model.addAttribute("article", art);
        return "upload/commonsUploadForm";
    }
    
    @Autowired
    AttachmentDao attDao;
    
    @Autowired
    ArticleDao articleDao;
     
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("uploadForm") FileUpload uploadForm,
                    Model map) {
         
       // List<MultipartFile> files = uploadForm.getUploadFiles();
    	MultipartFile file = uploadForm.getFile();
    	
    	Attachment newatt = new Attachment();
    	newatt.setActualFilename(file.getOriginalFilename());
    	try {
			newatt.setAttachment(file.getBytes());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	newatt.setAttachmentTitle("new attachment");
    	newatt.setAttachmentType("type");
    	
    	attDao.addEntity(newatt);
    	
        
       /* System.out.println(files.size() + " is the size of files");
 
        List<String> fileNames = new ArrayList<String>();
         
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
 
                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                //Handle file content - multipartFile.getInputStream()
 
            }
        }*/
    	
    	System.out.println("file " + file.getOriginalFilename());
    	map.addAttribute("files", newatt);
       
        return "upload/uploadSuccess";
    }
    
    @RequestMapping("/display/{id}")
    public String download(@PathVariable("id")
            Long id, HttpServletResponse response) {
         
        Attachment att = attDao.findAttachment(id);
        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" +att.getActualFilename()+ "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType("image/jpeg");
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
    
    //*************************TEST AJAX******************************************************************
    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) {                
  
      //0. notice, we have used MultipartHttpServletRequest
  
      //1. get the files from the request object
      Iterator<String> itr =  request.getFileNames();
  
      MultipartFile mpf = request.getFile(itr.next());
      
        Attachment newatt = new Attachment();
	  	newatt.setActualFilename(mpf.getOriginalFilename());
	  	try {
				newatt.setAttachment(mpf.getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  	newatt.setAttachmentTitle("new attachment");
	  	newatt.setAttachmentType("type");
	  	
	  	//attDao.addEntity(newatt);
	  	attDao.saveInMemory(newatt);
	  	
	      
	     /* System.out.println(files.size() + " is the size of files");
	
	      List<String> fileNames = new ArrayList<String>();
	       
	      if(null != files && files.size() > 0) {
	          for (MultipartFile multipartFile : files) {
	
	              String fileName = multipartFile.getOriginalFilename();
	              fileNames.add(fileName);
	              //Handle file content - multipartFile.getInputStream()
	
	          }
	      }*/	  	
      
      System.out.println(mpf.getOriginalFilename() +" uploaded!");
  
   /*   try {
                 //just temporary save file info into ufile
         ufile.length = mpf.getBytes().length;
         ufile.bytes= mpf.getBytes();
         ufile.type = mpf.getContentType();
         ufile.name = mpf.getOriginalFilename();
  
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }*/
  
      //2. send it back to the client as <img> that calls get method
      //we are using getTimeInMillis to avoid server cached image
  
      //return "<img src='http://localhost:8080/spring-mvc-file-upload/rest/cont/get/"+Calendar.getInstance().getTimeInMillis()+"' />";
      return mpf.getOriginalFilename() +" uploaded!";
  
   }
    
}
