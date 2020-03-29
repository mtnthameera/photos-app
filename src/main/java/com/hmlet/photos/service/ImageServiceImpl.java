package com.hmlet.photos.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
		imageRepo.save(imageModle);
		log.info("done save image info");

	}

	@Override
	public void editCaption(String caption, Long imageID) {
		log.info("edit catption starting");
		imageRepo.updateCaption(caption,imageID);
		log.info("caption edited");

	}

}
