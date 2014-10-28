package com.americanbanksystems.wiki.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.americanbanksystems.wiki.dao.AttachmentDao;
import com.americanbanksystems.wiki.domain.Attachment;
import com.americanbanksystems.wiki.web.helpers.FileUpload;
 
@Controller
@RequestMapping("/upload")
public class FileUploadController {
     
    @RequestMapping(method = RequestMethod.GET)
    public String displayForm() {
        return "upload/commonsUploadForm";
    }  
    
    @Autowired
    AttachmentDao attDao;
     
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
}
