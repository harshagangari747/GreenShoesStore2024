package com.store.greenShoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.greenShoes.model.Category;
import com.store.greenShoes.service.CategoryService;
@RestController
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:3001"}, methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	@GetMapping("/category")
	private List<Category> getAllProducts(@RequestParam(name="page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name="size", required = false, defaultValue = "100000") Integer size){
		return categoryService.getAllCategories(page, size);
	}
	
	@PostMapping("/category")
	private Category postCategory(@RequestBody Category category) {
		return categoryService.postCategory(category);
	}
	@GetMapping("/category/{name}")
	public Category getOne(@PathVariable("name") String name) {
		return categoryService.getOne(name);
	}

}
