package com.americanbanksystems.wiki.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 

import com.americanbanksystems.wiki.web.helpers.FileUpload;
 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
 
@Controller
public class FileUploadController {
     
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String displayForm() {
        return "commonsUploadForm";
    }
     
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("uploadForm") FileUpload uploadForm,
                    Model map) {
         
       // List<MultipartFile> files = uploadForm.getUploadFiles();
    	MultipartFile file = uploadForm.getFile();
        
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
         
        try {
			map.addAttribute("files",  file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "uploadSuccess";
    }
}
