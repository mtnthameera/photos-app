package com.hmlet.photos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hmlet.photos.service.ImageService;

@RestController
@EnableAutoConfiguration
public class PhotosController {

@Autowired
ImageService imageService;
	
	Logger log = LoggerFactory.getLogger(PhotosController.class);
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile imageFile, @RequestParam("caption") String caption) {
		
		log.info("***** uploadfilemethod");
		imageService.saveImageWithCaption(imageFile, caption);
		log.info("sending response");
		return new ResponseEntity<>("Image Saved Successfully", HttpStatus.OK);
	}
	
	@PutMapping("/editCaption")
	public ResponseEntity<String> editCaption(@RequestParam("caption") String caption, @RequestParam("imageID") Long id){
		imageService.editCaption(caption, id);
		return new ResponseEntity<>("Caption Upadated",HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePhoto(@RequestParam("imageID")Long id){
		imageService.deleteImage(id);
		return new ResponseEntity<>("Image Deleted",HttpStatus.OK);
	}
}
