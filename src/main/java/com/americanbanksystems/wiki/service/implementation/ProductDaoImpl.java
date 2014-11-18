package com.americanbanksystems.wiki.service.implementation;

/*
 * 
 *  @author BorisM 
 *  @date   10.26.2014
 * 
 */

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.americanbanksystems.wiki.dao.ProductDao;
import com.americanbanksystems.wiki.domain.Product;

@Repository("productDao")
public class ProductDaoImpl extends JPADao<Product, Long> implements ProductDao {
	
	public boolean removeProduct(Product product) {
		//todo fix this
		return false;
	}
	
	public boolean addProduct(Product product) {		
		addEntity(product);
		return true;
	}
	
	public boolean updateProduct(Product product) {		
		updateEntity(product);
		return true;
	}
	
	public Product findProduct(Long id) {
		Query productQuery = entityManager.createQuery(
                "from Product p where p.id = :prod");
		productQuery.setParameter("prod", id); 
		
		if (productQuery.getResultList().size() > 0) {
			return (Product) productQuery.getResultList().get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean productDeletable(Product prod) {
		Query productQuery = entityManager.createQuery(
                "from Article a where a.product = :prod");
		productQuery.setParameter("prod", prod); 
		
		if (productQuery.getResultList().size() > 0 || !prod.getCustom()) {
			return false;
		} else {
			return true;
		}		
	}
	
	public List<Product> list() {
		 return (List<Product>) entityManager.createQuery("select prod from Product prod").getResultList();
	}
	
}
