package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.util.ArrayList;
import java.util.List;

import com.americanbanksystems.wiki.domain.Attachment;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to Attachment, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface AttachmentDao extends GenericDAO<Attachment, Long> {	
	
    static List<Attachment> savedAttachments = new ArrayList<Attachment>();
	
	public List<Attachment> getSavedAttachments() ;
	
	Attachment findAttachment(Long id);
	boolean saveInMemory(Attachment att);
	boolean removeFromMemory(Attachment att);
	boolean clearMemoryStore();
}
