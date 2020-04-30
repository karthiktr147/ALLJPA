package com.mindtree.watchstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.watchstore.dto.ResponseBody;
import com.mindtree.watchstore.dto.StoreDTO;
import com.mindtree.watchstore.entity.Store;
import com.mindtree.watchstore.exception.WatchStoreApplicationException;
import com.mindtree.watchstore.service.StoreService;

@RestController
public class StoreController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/stores")
	public ResponseEntity<?> addStore(@Valid @RequestBody StoreDTO store) throws WatchStoreApplicationException {
		return new ResponseEntity<ResponseBody<StoreDTO>>(new ResponseBody<StoreDTO>(
				modelMapper.map(storeService.addStore(modelMapper.map(store, Store.class)), StoreDTO.class), null,
				"Store Added Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/stores")
	public ResponseEntity<?> displayAllStores() throws WatchStoreApplicationException {
		return new ResponseEntity<ResponseBody<List<StoreDTO>>>(new ResponseBody<List<StoreDTO>>(
				modelMapper.map(storeService.displayAllStore(), new TypeToken<List<StoreDTO>>() {
				}.getType()), null, "Store List Found", true), HttpStatus.OK);
	}

	@DeleteMapping("/stores/{storeId}")
	public ResponseEntity<?> deleteStore(@PathVariable long storeId) throws WatchStoreApplicationException {
		storeService.deleteStore(storeId);
		return new ResponseEntity<ResponseBody<Void>>(
				new ResponseBody<Void>(null, null, "Store Successfuly Deleted", true), HttpStatus.OK);
	}
}
