package com.americanbanksystems.wiki.service;

import java.util.List;

public interface GenericDAO<E, K> {

	void addEntity(E entity);

	void updateEntity(E entity);

	void removeEntity(E entity);

	List<E> list();

}
