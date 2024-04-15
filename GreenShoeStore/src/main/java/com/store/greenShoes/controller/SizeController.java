package com.store.greenShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.Size;
import com.store.greenShoes.repository.SizeRepository;



@RestController
@CrossOrigin(origins="http://localhost:3000",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class SizeController {
	@Autowired
	SizeRepository sizeRepository;
	
	@GetMapping("/sizes")
	private List<Size> getSizes(){
		return sizeRepository.findAll();
	}
	
	@PostMapping("/size")
	private List<Size> postSizes(@RequestBody List<Size> size ) {
		return sizeRepository.saveAll(size);
	}

}
