package com.store.greenShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.Color;
import com.store.greenShoes.repository.ColorRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class ColorController {
	@Autowired
	ColorRepository colorRepository;
	
	@GetMapping("/colors")
	private List<Color> getColors(){
		return colorRepository.findAll();
	}
	
	@PostMapping("/colors")
	private List<Color> postColors(@RequestBody List<Color> color ) {
		return colorRepository.saveAll(color);
	}

}
