package com.hmlet.photos.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	void saveImageWithCaption(MultipartFile image, String caption, boolean draft);

	void saveImagetoDisk(MultipartFile image, Path path);

	void editCaption(String caption, Long imageID);

	boolean deleteImage(Long imageId);

	List<String> getImageListByUser(Long userId);

	String getPathLink(Long imageId);

	List<String> getSortedImageList(String sortOrder);

	List<String> getPhotos(String type);

	void saveImageInfo(String imageName, String caption, String path, boolean draft);

	boolean validateImage(MultipartFile imageFile) throws IOException;
}
