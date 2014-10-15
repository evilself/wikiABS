package com.americanbanksystems.wiki.exception;

import com.americanbanksystems.wiki.domain.Article;

public class ArticleDeleteException extends Exception{
	
    private Article article;
 
    public ArticleDeleteException(Article article) {
        this.article = article;
    }
 
    public Article getArticle() {
        return article;
    }
	
}
