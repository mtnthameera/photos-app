package com.hmlet.photos.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hmlet.photos.service.ImageService;

import org.springframework.util.StringUtils;

@RestController
@EnableAutoConfiguration
public class PhotosController {

@Autowired
ImageService imageService;
	
	Logger log = LoggerFactory.getLogger(PhotosController.class);
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile) {
		
		log.info("***** uploadfilemethod");
		imageService.saveImageWithCaption(imageFile,"G-wagon");
		log.info("sending response");
		return new ResponseEntity<>("Image Saved Successfully", HttpStatus.OK);
	}
	
}
