package com.hmlet.photos.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	void saveImageWithCaption(MultipartFile image, String caption);
	void saveImagetoDisk(MultipartFile image,Path path);
	void saveImageInfo(String imageName, String caption, String path);
	void editCaption(String caption, Long imageID);
	boolean deleteImage(Long imageId);
	//Resource getImageAsResource(Long imageId);
	//List<Resource> getImageListByUser(Long userId); 
	List<String> getImageListByUser(Long userId);
	
	String getPathLink(Long imageId);
	
	String getSortedImageList();
}
