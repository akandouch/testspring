package com.aka.testspring.dao;

import java.util.List;

public interface GenericDAO<T> {
		
	public List<T> getAll();
	public T findOne(Integer id);
	public boolean exists(T t);
	public void delete(T t);
	public T add(T t);

}
