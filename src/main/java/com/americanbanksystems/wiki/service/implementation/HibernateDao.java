package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.americanbanksystems.wiki.service.GenericDAO;
 
/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * @see SessionFactory
 */

//Transactional means HibernateDao will be @Transactional. Add sessionFactory bean in persistence-beans.xml. 
@Transactional(propagation= Propagation.REQUIRED, readOnly=false) 
public class HibernateDao<E, K extends Serializable> implements GenericDAO<E, K> {
 
    private SessionFactory sessionFactory;
    protected Class<? extends E> daoType;
 
    public HibernateDao() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }
 
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

	public List<E> list() {
		 return currentSession().createCriteria(daoType).list();
	}

	public boolean addEntity(E entity) {
		currentSession().save(entity);
		return true;
	}

	public boolean updateEntity(E entity) {
		currentSession().saveOrUpdate(entity);
		return true;
	}

	public boolean removeEntity(E entity) {
		currentSession().delete(entity);
		return true;
		
	} 
    
}