package com.aka.testspring.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aka.testspring.repository.GenericRepo;

@Service
public class ProductDAOImpl<Product> implements GenericDAO<Product> {
	
	@Autowired
	GenericRepo<Product> productRepo;

	@Override
	public List<Product> getAll() {
		return this.productRepo.findAll();
	}

	@Override
	public Product findOne(Integer id) {
		return this.productRepo.find(id);
	}

	@Override
	public boolean exists(Product p) {
		return this.productRepo.contains(p);
	}

	@Override
	public void delete(Product p) {
		this.productRepo.delete(p);
	}
	
	@Override
	public Product add(Product p) {
		return this.productRepo.add(p);
	}
	

}
