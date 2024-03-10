package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
