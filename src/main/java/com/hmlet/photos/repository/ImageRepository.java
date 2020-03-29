package com.hmlet.photos.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hmlet.photos.modle.Image;

@Repository
public interface ImageRepository extends CrudRepository<Image, String>{
	
	@Modifying
	@Transactional
	@Query("update Image img set img.caption = ?1 where img.id = ?2")
	int updateCaption(String caption, Long imageId);
}
