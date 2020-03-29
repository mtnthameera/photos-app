package com.hmlet.photos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hmlet.photos.modle.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, String>{
	
}
