package com.example.Biglots.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Biglots.Entity.Product;
import com.example.Biglots.Repository.ProductRepo;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepo productRepo;
	
	@PostMapping(value="/api/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
	
	return new ResponseEntity<Product> (productRepo.save(product),HttpStatus.CREATED);
		//System.out.println(product);
		
	}

	@GetMapping(value="/api/products")
	public ResponseEntity<List<Product>> getProducts(){
		return new ResponseEntity<>(productRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping(value="/api/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id){
	Optional<Product> product = productRepo.findById(id);
	if(product.isPresent())
	{
		return new ResponseEntity<>(product.get(),HttpStatus.OK);
	}
	else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	
	
	@PutMapping(value="/api/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product produ){
	Optional<Product> product = productRepo.findById(id);
	if(product.isPresent())
	{
		product.get().setName(produ.getName());
		product.get().setQuantity(produ.getQuantity());
		product.get().setPrice(produ.getPrice());
		product.get().setImage(produ.getImage());
		return new ResponseEntity<>(productRepo.save(product.get()),HttpStatus.OK);
	}
	else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	@DeleteMapping(value="/api/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id){
	Optional<Product> product = productRepo.findById(id);
	if(product.isPresent())
	{
		productRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	}
	
}


