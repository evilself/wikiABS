package com.americanbanksystems.wiki.web.helpers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.americanbanksystems.wiki.dao.ArticleDao;
import com.americanbanksystems.wiki.dao.UserDao;
import com.americanbanksystems.wiki.dao.UserRoleDao;
 
/**
 * Small util helper for generating entities to simulate real system.
 */
@Service //NOTE SERVICE here
public final class EmailSenderService {
 
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserRoleDao userRoleDao;
 
    @Autowired
    private ArticleDao articleDao;
    
    @Autowired
    UserUtils utils;
    
    @Autowired
    private JavaMailSender mailSender;
  
    public void createEmail() {
    	
    	MimeMessage message = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message);
    	try {
			helper.setSubject("Zdravei");
			helper.setTo("havka.lyoteva@gmail.com");
			helper.setText("LVOE YOU LOVE YOU LOVE YOU", true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 sendEmail(message);
       
    }
    
    private void sendEmail(MimeMessage mimeMsg) {
    	mailSender.send(mimeMsg);
    }
    
}