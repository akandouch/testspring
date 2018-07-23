package com.aka.controller;

import java.util.List;

import com.aka.testspring.models.Product;

public interface GenericController<T> {

	public List<T> getAll();
	public T add(T t);
	public T delete(T t);
}
