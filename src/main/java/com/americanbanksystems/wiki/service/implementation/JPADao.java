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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.americanbanksystems.wiki.service.GenericDAO;
 
/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * @see SessionFactory
 */

//Transactional means HibernateDao will be @Transactional. Add sessionFactory bean in persistence-beans.xml. 
@Transactional(propagation= Propagation.REQUIRED, readOnly=false) 
public class JPADao<E, K extends Serializable> implements GenericDAO<E, K> {
 
    @PersistenceContext
    EntityManager entityManager;
    
    protected Class<? extends E> daoType;
 
    public JPADao() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }
 
    public List<E> list() {
		 return null;
	}

	public boolean addEntity(E entity) {
		entityManager.persist(entity);
		return true;
	}

	public boolean updateEntity(E entity) {
		entityManager.merge(entity);
		return true;
	}

	public boolean removeEntity(E entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		return true;
		
	} 
    
}