package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.AttachmentDao;
import com.americanbanksystems.wiki.domain.Attachment;

@Repository("attachmentDao") //Component in persistance layer for IoC
public class AttachmentDaoImpl extends HibernateDao<Attachment, Long> implements AttachmentDao{	

	public boolean removeAttachment(Attachment attachment) {
		Query attachmentQuery = currentSession().createQuery(
                "from Attachment a where a = :art");
		attachmentQuery.setParameter("art", attachment);            
 
        // ok, remove as usual
        removeEntity(attachment);
        return true;		
	}

	public boolean addAttachment(Attachment attachment) {
		// TODO Auto-generated method stub
		addEntity(attachment);
		return true;
	}

	public boolean updateAttachment(Attachment attachment) {
		updateEntity(attachment);
		return true;
	}

	public Attachment findAttachment(Long id) {
		Query attachmentQuery = currentSession().createQuery(
                "from Attachment a where a.id = :art");
		attachmentQuery.setParameter("art", id); 
		
		if (attachmentQuery.list().size() > 0) {
			return (Attachment) attachmentQuery.list().get(0);
		} else {
			return null;
		}		
		
	}

	@Override
	public boolean saveInMemory(Attachment att) {
		getSavedAttachments().add(att);
		System.out.println(getSavedAttachments().size() + " is the size of our in-memory attachemnt list");
		return true;
	}

	@Override
	public List<Attachment> getSavedAttachments() {
		return this.savedAttachments;
	}

	@Override
	public boolean removeFromMemory(Attachment att) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean clearMemoryStore() {
		getSavedAttachments().clear();
		return true;
	}	
	
	public void flushSession() {		
		currentSession().flush();
	}
	
}
