package com.americanbanksystems.wiki.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.americanbanksystems.wiki.service.GenericDAO;

public class ArticleDAO<E, K> implements GenericDAO<E, K> {

	protected List<E> articles = new ArrayList<E>();

	public void addEntity(E article) {
		// TODO Auto-generated method stub
		articles.add(article);
	}

	public void updateEntity(E article) {
		// TODO Auto-generated method stub

	}

	public void removeEntity(E article) {
		// TODO Auto-generated method stub
		articles.remove(article);
	}

	public List<E> list() {
		return articles;
	}
}
