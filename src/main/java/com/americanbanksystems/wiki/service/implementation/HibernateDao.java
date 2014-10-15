package com.americanbanksystems.wiki.service.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 

import com.americanbanksystems.wiki.service.GenericDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
 
/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * @see SessionFactory
 */
@Transactional(propagation= Propagation.REQUIRED, readOnly=false) //Transactional means HibernateDao will be transactional. Add sessionFactory bean in persistence-beans.xml
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

	public void addEntity(E entity) {
		currentSession().save(entity);		
	}

	public void updateEntity(E entity) {
		currentSession().saveOrUpdate(entity);
	}

	public void removeEntity(E entity) {
		currentSession().delete(entity);
		
	} 
    
}