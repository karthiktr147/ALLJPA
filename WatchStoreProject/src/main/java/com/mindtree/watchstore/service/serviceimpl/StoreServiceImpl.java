package com.mindtree.watchstore.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.watchstore.entity.Store;
import com.mindtree.watchstore.exception.service.WatchStoreServiceException;
import com.mindtree.watchstore.exception.service.custom.StoreAlreadyPresentException;
import com.mindtree.watchstore.exception.service.custom.StoreNotFoundException;
import com.mindtree.watchstore.repository.StoreRepository;
import com.mindtree.watchstore.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public Store addStore(Store store) throws WatchStoreServiceException {
		if (storeRepository.findByName(store.getName()).isPresent())
			throw new StoreAlreadyPresentException("Store Is Already Present");
		//store.getWatches().forEach(i -> i.setStore(store));
		storeRepository.save(store);
		return storeRepository.findByName(store.getName()).get();
	}

	@Override
	public Store getStoreByName(String storeName) throws WatchStoreServiceException {
		return storeRepository.findByName(storeName).orElseThrow(() -> new StoreNotFoundException("Store Not Found"));

	}

	@Override
	public Store getStoreById(long storeId) throws WatchStoreServiceException {
		return storeRepository.findById(storeId).orElseThrow(() -> new StoreNotFoundException("Store Not Found"));
	}

	@Override
	public List<Store> displayAllStore() throws WatchStoreServiceException {
		if (storeRepository.findAll().isEmpty()) {
			throw new StoreNotFoundException("There Is No Store To Display");
		}
		return storeRepository.findAll();
	}

	@Override
	public void deleteStore(long storeId) throws WatchStoreServiceException {
		Store store = storeRepository.findById(storeId)
				.orElseThrow(() -> new StoreNotFoundException("Store Not Found"));
		storeRepository.delete(store);
	}
}
