package com.aka.testspring;

import java.io.File;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.hibernate.SessionFactory;
import org.hibernate.engine.transaction.jta.platform.internal.TransactionManagerAccess;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.aka.testspring.repository"})
public class PersistenceConfig {
 
   @Autowired
   private Environment env;
   
   
   @Bean
   @Autowired
   public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
	   LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	   emf.setDataSource(dataSource);
	   HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
	   hjva.setShowSql(true);
	   emf.setJpaVendorAdapter(hjva);
	   //emf.setJpaDialect(new HibernateJpaDialect());
	   emf.setJpaProperties(hibernateProperties());
	   emf.setPackagesToScan("com.aka.testspring.models");
	   return emf;
   }
 
   //@Bean
   @Autowired
   public SessionFactory sessionFactory(DataSource dataSource) {
	   
	   LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);    
	    builder.addProperties(hibernateProperties());   
	    builder.scanPackages("com.aka.testspring.models");    
	    return builder.buildSessionFactory();
   }
 
   @Bean
   public DataSource restDataSource() {
	   DriverManagerDataSource dataSource = new DriverManagerDataSource();
	   dataSource.setDriverClassName("org.h2.Driver");
	   dataSource.setUrl("jdbc:h2:file:~/h2/aka");
	   dataSource.setUsername("sa");
	   dataSource.setPassword("sa");
 
      return dataSource;
   }
 
   /*@Bean
   @Autowired
   public HibernateTransactionManager transactionManager(
     SessionFactory sessionFactory) {
      HibernateTransactionManager txManager
       = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory);
 
      return txManager;
   }*/
 
   @Bean
   @Autowired
   public JpaTransactionManager getTransactionManager(EntityManagerFactory emf) {
	   JpaTransactionManager jtm = new JpaTransactionManager();
	   jtm.setEntityManagerFactory(emf);
	   return jtm;
   }
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties hibernateProperties() {
      return new Properties() {
         {
            setProperty("hibernate.hbm2ddl.auto","update");
            setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
            setProperty("hibernate.globally_quoted_identifiers","true");
         }
      };
   }
}
