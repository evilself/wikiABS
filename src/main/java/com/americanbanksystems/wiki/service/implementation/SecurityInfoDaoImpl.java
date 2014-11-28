package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   11.28.2014
 * 
 */

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.SecurityInfoDao;
import com.americanbanksystems.wiki.domain.SecurityInfo;

@Repository("securityInfoDao")
public class SecurityInfoDaoImpl extends HibernateDao<SecurityInfo, Long> implements SecurityInfoDao {

	public boolean removeSecurityInfo(SecurityInfo sec) {
		if(sec != null) {			
			removeEntity(sec);
			return true;
		}		
        return false;
	}
	
	public boolean addSecurityInfo(SecurityInfo sec) {		
		addEntity(sec);
		return true;
	}
	
	public boolean updateSecurityInfo(SecurityInfo sec) {		
		updateEntity(sec);
		return true;
	}
	
	public SecurityInfo findSecurityInfo(Long id) {
		Query infoQuery = currentSession().createQuery(
                "from SecurityInfo s where s.id = :sec");
		infoQuery.setParameter("sec", id); 
		
		if (infoQuery.list().size() > 0) {
			SecurityInfo secInfo =  (SecurityInfo) infoQuery.list().get(0);
			return secInfo;
		} else {
			return null;
		}
	}
	
}
