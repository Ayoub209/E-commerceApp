package com.example.demo.web;

import java.nio.file.Files;

import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entities.Product;

@CrossOrigin("*")
@RestController
public class catalogueController {
	private ProductRepository productRepository;
	
	public catalogueController(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	@GetMapping(path="/photoProduct/{id}",produces =   MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		Product p=productRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/icones/products/"+p.getPhoto()));
	}
	
   @PostMapping(path="/uploadPhotoPr/{id}")
   public void upoloadPhoto(MultipartFile file,@PathVariable long id) throws Exception {
	   Product p=productRepository.findById(id).get();
	   p.setPhoto(file.getOriginalFilename());
	   Files.write(Paths.get(System.getProperty("user.home")+"/icones/products/"+p.getPhoto()),file.getBytes());
	   productRepository.save(p);
   }
	
}
