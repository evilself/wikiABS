package com.americanbanksystems.wiki.wikiABS;

import static org.junit.Assert.*;

import java.util.List;

import com.americanbanksystems.wiki.domain.Article;
import com.americanbanksystems.wiki.service.GenericDAO;
import com.americanbanksystems.wiki.service.implementation.ArticleDAO;

import org.junit.Before;
import org.junit.Test;

public class ArticleInMemoryDaoTestCase {

	private GenericDAO<Article, Long> articleDao = new ArticleDAO<Article, Long>();

	@Before
	public void setUp() {
		for (int i = 0; i < 5; i++) {
			Article e = new Article();
			e.setTitle("Hey " + i);
			e.setDescription("description " + i);
			articleDao.addEntity(e);
		}
	}

	@Test
	public void testAdd() {
		int oldSize = articleDao.list().size();
		Article e = new Article();
		e.setTitle("BOB Title");
		e.setDescription("BOB DESC");
		articleDao.addEntity(e);
		int newSize = articleDao.list().size();

		assertFalse(oldSize == newSize);
	}

	@Test
	public void testRemove() {
		int oldSize = articleDao.list().size();
		Article e = articleDao.list().get(0);
		articleDao.removeEntity(e);
		int newSize = articleDao.list().size();

		assertFalse(oldSize == newSize);
	}

	@Test
	public void testUpdate() {
		// TODO: need real implementation
	}

	@Test
	public void testList() {
		List<Article> list = articleDao.list();
		assertNotNull(list);
		assertFalse(list.isEmpty());
	}

}
