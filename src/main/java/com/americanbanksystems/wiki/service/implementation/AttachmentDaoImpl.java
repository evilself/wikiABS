package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

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
}
