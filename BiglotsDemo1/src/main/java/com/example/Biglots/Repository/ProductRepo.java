package com.example.Biglots.Repository;

import org.hibernate.query.ReturnableType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Biglots.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	

}
