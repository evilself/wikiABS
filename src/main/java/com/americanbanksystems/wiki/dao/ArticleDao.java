package com.americanbanksystems.wiki.dao;

import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.service.GenericDAO;

//This class specifies any other services related to Article, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface ArticleDao extends GenericDAO<Article, Long> {
	boolean removeArticle(Article article);
	boolean addArticle(Article article);
	boolean updateArticle(Article article);
	Article findArticle(Long id);
}
