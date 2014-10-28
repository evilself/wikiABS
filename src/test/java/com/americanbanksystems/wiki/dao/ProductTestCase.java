package com.americanbanksystems.wiki.dao;

/*
 * 
 *  @author BorisM 
 *  @date   10.20.2014
 * 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.americanbanksystems.wiki.domain.Product;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class ProductTestCase extends DomainAwareBase{
	 
    @Autowired
    private ProductDao productDao;   
 
    public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Test
    public void testAdd() {
        int size = productDao.list().size();       
        Product p = new Product("CompliancePro");
        productDao.addEntity(p);
      
        // list should have one more employee now
        assertTrue (size < productDao.list().size());
        
    }
     
    @Test
    public void testUpdate() {    	
    	Product p = new Product("CompliancePro");
    	productDao.addEntity(p);
        p.setProductName("CompliancePro Loans");
 
        productDao.updateEntity(p);
        Product found = productDao.findProduct(p.getId());
        assertEquals("CompliancePro Loans", found.getProductName());
    }
 
    @Test
    public void testFind() {
    	Product p = new Product("CompliancePro");
    	productDao.addEntity(p);
 
    	Product found = productDao.findProduct(p.getId());
        assertEquals(found, p);
    }
 
    @Test
    public void testList() {
        assertEquals(0, productDao.list().size());
         
        List<Product> products = Arrays.asList(
        		new Product("CompliancePro"),
        		new Product("CompliancePro Loans"),
        		new Product("Bank Manager"));
        for (Product product : products) {
        	productDao.addEntity(product);
        }
 
        List<Product> found = productDao.list();
        assertEquals(3, found.size());
        for (Product product : found) {
            assertTrue(products.contains(product));
        }
    }
 
    @Test
    public void testRemove() {
    	Product p = new Product("CompliancePro");
    	productDao.addEntity(p);
 
        // successfully added
        assertEquals(p, productDao.findProduct(p.getId()));
 
        // try to remove
        productDao.removeEntity(p);
        assertNull(productDao.findProduct(p.getId()));
    }    
}
