package com.hmlet.photos.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hmlet.photos.service.ImageService;

@RestController
@EnableAutoConfiguration
public class PhotosController {

	@Autowired
	ImageService imageService;

	Logger log = LoggerFactory.getLogger(PhotosController.class);

	@PostMapping("/photo")
	public ResponseEntity<String> uploadImage(@RequestParam("photo") MultipartFile imageFile,
			@RequestParam("caption") String caption, @RequestParam("draft") boolean draft) {
		imageService.saveImageWithCaption(imageFile, caption, draft);
		return new ResponseEntity<>("Image Saved Successfully...", HttpStatus.OK);
	}

	@PutMapping("/photo/{imageId}/caption")
	public ResponseEntity<String> editCaption(@RequestParam("caption") String caption,
			@PathVariable("imageId") Long id) {
		imageService.editCaption(caption, id);
		return new ResponseEntity<>("Caption Upadated...", HttpStatus.OK);
	}

	@DeleteMapping("/photo/{imageId}")
	public ResponseEntity<String> deletePhoto(@PathVariable("imageId") Long id) {
		imageService.deleteImage(id);
		return new ResponseEntity<>("Image Deleted...", HttpStatus.OK);
	}

	@GetMapping("/photo/{type}")
	public ResponseEntity<List<String>> getImage(@PathVariable("type") String type) {
		List<String> resource = imageService.getPhotos(type);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/json")).body(resource);
	}

	@GetMapping("/photo/user/{userId}")
	public ResponseEntity<List<String>> filterImageByUser(@PathVariable("userId") Long userId) {
		List<String> resourceList = imageService.getImageListByUser(userId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/json")).body(resourceList);
	}

	@GetMapping("/photo/sort/{order}")
	public ResponseEntity<List<String>> getOrderedImageList(@PathVariable("order") String order) {
		List<String> resourceList = imageService.getSortedImageList(order);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/json")).body(resourceList);
	}

}
