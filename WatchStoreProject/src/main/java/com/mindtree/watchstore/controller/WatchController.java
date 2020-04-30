package com.mindtree.watchstore.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.watchstore.dto.PriceDTO;
import com.mindtree.watchstore.dto.ResponseBody;
import com.mindtree.watchstore.dto.WatchDTO;
import com.mindtree.watchstore.entity.Watch;
import com.mindtree.watchstore.exception.WatchStoreApplicationException;
import com.mindtree.watchstore.service.WatchService;

@RestController
public class WatchController {

	@Autowired
	private WatchService watchService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/watches/{storeId}")
	public ResponseEntity<?> addWatch(@PathVariable long storeId, @Valid @RequestBody WatchDTO watch)
			throws WatchStoreApplicationException {

		return new ResponseEntity<ResponseBody<WatchDTO>>(new ResponseBody<WatchDTO>(
				modelMapper.map(watchService.addWatch(modelMapper.map(watch, Watch.class), storeId), WatchDTO.class),
				null, "Watch Added Successfully", true), HttpStatus.OK);
	}

	@PutMapping("/watches/{watchId}")
	public ResponseEntity<?> updateWatchPrice(@PathVariable long watchId, @Valid @RequestBody PriceDTO price)
			throws WatchStoreApplicationException {

		return new ResponseEntity<ResponseBody<WatchDTO>>(new ResponseBody<WatchDTO>(
				modelMapper.map(watchService.updateWatchPrice(watchId, price.getPrice()), WatchDTO.class), null,
				"Watch Price Updated Successfully", true), HttpStatus.OK);

	}

	@DeleteMapping("/watches/{watchId}")
	public ResponseEntity<?> deleteWatch(@PathVariable long watchId) throws WatchStoreApplicationException {
		watchService.deleteWatch(watchId);
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, null, "Watch Successfuly Deleted", true), HttpStatus.OK);
	}
}
