package com.aka.testspring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.aka.testspring.models.Product;

@Repository
@Transactional
public class ProductRepoImpl implements GenericRepo<Product> {
	
	//@Autowired
	//SessionFactory sessionFactory;
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public Product add(Product p) {
		/*try {
			this.sessionFactory.getCurrentSession().persist(p);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}*/
		this.em.persist(p);
		return p;
	}

	@Override
	public void delete(Product p) {
		//this.sessionFactory.getCurrentSession().delete(p);
		this.em.remove(p);
	}

	@Override
	public boolean contains(Product p) {
		// TODO Auto-generated method stub
		return this.em.contains(p);
	}

	@Override
	public List<Product> findAll() {
		//return this.sessionFactory.getCurrentSession().createQuery("from Product",Product.class).list();
		return this.em.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public Product find(Integer id) {
		//return this.sessionFactory.getCurrentSession().find(Product.class, id);
		return this.em.find(Product.class, id);
	}

}
