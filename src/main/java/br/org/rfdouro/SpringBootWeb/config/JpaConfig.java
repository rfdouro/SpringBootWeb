/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.SpringBootWeb.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author romulo.douro
 */
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.org.rfdouro.SpringBootWeb.repositories")
public class JpaConfig {


 @Bean
 public DataSource dataSource() {
  DataSourceBuilder dsb = DataSourceBuilder.create();
  dsb.driverClassName("org.h2.Driver");
  dsb.url("jdbc:h2:mem:dbteste");
  dsb.username("sa");
  dsb.password("");
  return dsb.build();
 }
 
 @Bean
 public LocalContainerEntityManagerFactoryBean entityManagerFactory()
         throws NamingException {
  LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
  em.setDataSource(dataSource());
  em.setPackagesToScan("br.org.rfdouro.SpringBootWeb.models");
  em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
  em.setJpaProperties(additionalProperties());
  return em;
 }

 @Bean
 public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
  JpaTransactionManager transactionManager = new JpaTransactionManager();
  transactionManager.setEntityManagerFactory(emf);
  return transactionManager;
 }

 final Properties additionalProperties() {
  final Properties hibernateProperties = new Properties();
  hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
  hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
  hibernateProperties.setProperty("hibernate.show_sql", "false");
  hibernateProperties.setProperty("hibernate.format_sql", "true");
  hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
  return hibernateProperties;
 }
}
