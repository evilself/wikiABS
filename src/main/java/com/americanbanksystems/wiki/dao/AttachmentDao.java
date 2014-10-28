package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import com.americanbanksystems.wiki.domain.Attachment;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to Attachment, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface AttachmentDao extends GenericDAO<Attachment, Long> {	
	Attachment findAttachment(Long id);
}
