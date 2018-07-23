package com.aka.testspring;


import javax.naming.NamingException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.naming.factory.BeanFactory;
import org.hibernate.event.spi.PostCollectionRecreateEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.HandlerMapping;

import com.aka.testspring.dao.GenericDAO;
import com.aka.testspring.models.Product;
import com.aka.testspring.SpringConfig;
import com.aka.testspring.PersistenceConfig;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Hashtable;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringConfig.class,PersistenceConfig.class})
@WebAppConfiguration
public class AppTest 
{
	
	@Autowired
	WebApplicationContext wac;
	
	MockMvc mockmvc;
	
	@Autowired
	GenericDAO<Product> productService;
	
	@Before
	public void init() {
		mockmvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

    @Test
    public void productDAOListTest() {
    	Assert.notNull(productService,"The spring dependency injection does not work !");
    }
    
    
    @Test
    public void mvcTest() {
    	try {
			mockmvc.perform(get("/product/")).andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void addProductTest() {
    	Product p  = new Product();
    	Assert.isNull(p.getId(),"contains an id before persisted");
    	this.productService.add(p);
    	Assert.notNull(p.getId(),"No persisted, no id created" + p.getId());
    }
    
    @Test
    public void findProductTest() {
    	
    	Product p = new Product();
    	this.productService.add(p);
    	
    	Assert.notNull(this.productService.findOne(p.getId()),"Cannot find entity");
    }

    
    @Test
    @Transactional
    public void deleteProductTest() {
    	Product p = new Product("test","123");
    	p = this.productService.add(p);
    	System.out.println(p.getId());
    	this.productService.delete(p);
    	Assert.isNull(this.productService.findOne(p.getId()),"Cannot find entity");
    }
    
    /*@Test
    public void existsProductTest() {
    	Product p = new Product("test", "testref");
    	this.productService.add(p);
    	Assert.isTrue(this.productService.exists(p),"exist method does not work !");
    }*/
}
