package com.store.greenShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.greenShoes.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}
