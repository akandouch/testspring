package com.aka.testspring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface GenericRepo<T> {

		public T add(T t);
		public void delete(T t);
		public boolean contains(T t);
		public List<T> findAll();
		public T find(Integer id);

}
