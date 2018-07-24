package com.aka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aka.testspring.dao.GenericDAO;
import com.aka.testspring.models.Product;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements GenericController<Product>{
	
	@Autowired
	GenericDAO<Product> productService;

	@GetMapping("/")
	@ResponseBody
	public String testdd() {
		return "ok";
	}

	@Override
	@GetMapping(value="/list", produces="application/json")
	@CrossOrigin(origins="*")
	public List<Product> getAll() {
		System.out.println("*** CALL *** : list of products");
		return this.productService.getAll();
	}
	
	@Override
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins="*")
	public Product add(@RequestBody Product p) {
		System.out.println("*** CALL *** : add of products");
		productService.add(p);
		return p;
	}
	
	@Override
	@DeleteMapping(value = "/", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins="http://localhost:4200")
	public Product delete(@RequestBody Product p) {
		System.out.println("*** CALL *** : delete of products");
		productService.delete(p);
		return p;
	}

}
