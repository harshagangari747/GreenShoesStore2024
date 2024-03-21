package com.store.greenShoes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.store.greenShoes.model.Category;
import com.store.greenShoes.repository.CategoryRepository;
@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories(Integer page, Integer size) {
		PageRequest pageable = PageRequest.of(page, size);
		return categoryRepository.findAll(pageable).getContent();
	}

	public Category postCategory(Category category) {
		return categoryRepository.save(category);
	}

	public Category getOne(String name) {
		
			return categoryRepository.getByCategory(name);
		
	}
}
