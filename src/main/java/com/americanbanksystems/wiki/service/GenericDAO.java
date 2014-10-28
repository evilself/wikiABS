package com.americanbanksystems.wiki.service;

/*
 * 
 *  @author BorisM 
 *  @date   10.18.2014
 * 
 */

import java.util.List;

public interface GenericDAO<E, K> {

	boolean addEntity(E entity);

	boolean updateEntity(E entity);

	boolean removeEntity(E entity);

	List<E> list();

}
