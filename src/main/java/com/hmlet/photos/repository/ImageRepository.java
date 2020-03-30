package com.hmlet.photos.repository;

import java.util.List;

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
	
	@Query("select img.imagePath from Image img where img.id = ?1")
	String getImageLocation(Long imageId);
	
	@Modifying
	@Transactional
	@Query("delete from Image img where img.id= ?1")
	void deleteImageInfo(Long imageId);
	
	@Query("select img.id from Image img where img.userId = ?1")
    List<Long> getImageIdListForUser(Long Id);
	
	@Query("select img.id from Image img order by img.publishTime ASC")
	List<Long> getSortedImageListASC();
	
	@Query("select img.id from Image img order by img.publishTime DESC")
	List<Long> getSortedImageListDESC();
	
	@Query("select img.id from Image img ")
    List<Long> getAllPhotos();
	
	@Query("select img.id from Image img where img.userId = ?1")
    List<Long> getMyPhotos(Long Id);
	
	@Query("select img.id from Image img where img.draft = 1")
    List<Long> getDraftPhotos();
	
	
}
