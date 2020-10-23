package com.example.demo.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.Product;

@CrossOrigin("*")
@RepositoryRestResource(path = "produit")
public interface ProductRepository extends JpaRepository<Product, Long>{

	@RestResource(path = "/selectedproducts")
	public List<Product> findBySelectedIsTrue();
	@RestResource(path = "/productname")
	public List<Product> findBynomContains(@Param("mc")String mc);
	
	@RestResource(path = "/productnamebyPage")
	public Page<Product> findBynomContains(@Param("mc")String mc,Pageable pageable);
	
	@RestResource(path = "/promoproducts")
	public List<Product> findBypromotionIsTrue();
	
	@RestResource(path = "/availproducts")
	public List<Product> findByavailableIsTrue();
}
