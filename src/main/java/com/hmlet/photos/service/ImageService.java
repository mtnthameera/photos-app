package com.hmlet.photos.service;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	void saveImageWithCaption(MultipartFile image, String caption);
	void saveImagetoDisk(MultipartFile image,Path path);
	void saveImageInfo(String imageName, String caption, String path);
	void editCaption(String caption, Long imageID);
}
