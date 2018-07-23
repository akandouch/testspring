package com.aka.domain;

import com.aka.testspring.dao.GenericDAO;
import com.aka.testspring.models.Product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductDomain {

	GenericDAO<Product> productService;
	
	public List<Product> getAll(){
		return this.productService.getAll();
	}
	
}
