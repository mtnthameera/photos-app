package com.hmlet.photos.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hmlet.photos.modle.Image;
import com.hmlet.photos.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Value("${image.basepath}")
	String basePath;

	@Autowired
	ImageRepository imageRepo;

	Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

	@Override
	public void saveImageWithCaption(MultipartFile imageFile, String caption) {
		String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
		Path path = Paths.get(basePath, fileName);
		saveImagetoDisk(imageFile, path);
		saveImageInfo(fileName, caption, path.toString());
	}

	@Override
	public void saveImagetoDisk(MultipartFile image, Path path) {

		try {
			Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			log.info("file compy done.....");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void saveImageInfo(String imageName, String caption, String path) {
		log.info("inside save image info");
		Image imageModle = new Image();
		imageModle.setName(imageName);
		imageModle.setCaption(caption);
		imageModle.setImagePath(path);
		// TODO : User ID is hardcoded.
		imageModle.setUserId((long) 123);
		imageRepo.save(imageModle);
		log.info("done save image info");

	}

	@Override
	public void editCaption(String caption, Long imageID) {
		log.info("edit catption starting");
		imageRepo.updateCaption(caption, imageID);
		log.info("caption edited");

	}

	// Deletes image file from the File System
	@Override
	public boolean deleteImage(Long imageId) {
		boolean deleted = false;
		try {
			if (Files.deleteIfExists(getPath(imageId))) {
				imageRepo.deleteImageInfo(imageId);
				deleted = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deleted;

	}

	// Return Path of image by ImageID
	public Path getPath(Long imageID) {
		Path path = Paths.get(imageRepo.getImageLocation(imageID));
		return path;
	}

	/*
	 * @Override public Resource getImageAsResource(Long imageId) { Path path =
	 * getPath(imageId); try { Resource resource = new UrlResource(path.toUri());
	 * return resource; } catch (MalformedURLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } return null; }
	 */

	// Get all images belong to user

	@Override
	public List<String> getImageListByUser(Long userId) {
		List<String> imageList = new ArrayList<>();
		for (Long imageId : imageRepo.getImageIdListForUser(userId)) {
			imageList.add(getPathLink(imageId));
		}
		return imageList;
	}

	// return link to image
	@Override
	public String getPathLink(Long imageId) {
		return ServletUriComponentsBuilder.fromCurrentContextPath().path(imageRepo.getImageLocation(imageId)).toUriString();

	}

	@Override
	public String getSortedImageList() {
		// TODO Auto-generated method stub
		return null;
	}

}
