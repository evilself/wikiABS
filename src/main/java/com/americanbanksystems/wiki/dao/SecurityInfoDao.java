package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   11.28.2014
 * 
 */

import com.americanbanksystems.wiki.domain.SecurityInfo;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to SecurityInfo, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface SecurityInfoDao extends GenericDAO<SecurityInfo, Long> {	
	//NONE
	boolean removeSecurityInfo(SecurityInfo sec);
	SecurityInfo findSecurityInfo(Long id);
}
