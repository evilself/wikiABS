package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.26.2014
 * 
 */

import com.americanbanksystems.wiki.domain.Product;
import com.americanbanksystems.wiki.service.GenericDAO;

//This interface specifies any other services related to User, rather than the CRUD operations that we have set up in our
//GenericDAO
public interface ProductDao extends GenericDAO<Product, Long> {	
	Product findProduct(Long id);
	boolean productDeletable(Product prod);
}
