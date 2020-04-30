package com.mindtree.watchstore.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.watchstore.entity.Store;
import com.mindtree.watchstore.entity.Watch;
import com.mindtree.watchstore.exception.service.WatchStoreServiceException;
import com.mindtree.watchstore.exception.service.custom.WatchAlreadyPresentException;
import com.mindtree.watchstore.exception.service.custom.WatchNotFoundException;
import com.mindtree.watchstore.repository.WatchRepository;
import com.mindtree.watchstore.service.StoreService;
import com.mindtree.watchstore.service.WatchService;

@Service
public class WatchServiceImpl implements WatchService {

	@Autowired
	private WatchRepository watchRepository;

	@Autowired
	private StoreService storeService;

	@Override
	public Watch addWatch(Watch watch, long storeId) throws WatchStoreServiceException {
		Store store = storeService.getStoreById(storeId); // OR use GetByName();
		if (watchRepository.findByName(watch.getName()).isPresent())
			throw new WatchAlreadyPresentException("Watch Is Already Present");
		watch.setStore(store);
		watchRepository.save(watch);
		return watchRepository.findByName(watch.getName()).get();
	}

	@Override
	public Watch updateWatchPrice(long watchId, double price) throws WatchStoreServiceException {
		Watch watch = watchRepository.findById(watchId)
				.orElseThrow(() -> new WatchNotFoundException("Watch Not Found"));
		watch.setPrice(price);
		return watchRepository.save(watch);
	}

	@Override
	public void deleteWatch(long watchId) throws WatchStoreServiceException {
		Watch watch = watchRepository.findById(watchId)
				.orElseThrow(() -> new WatchNotFoundException("Watch Not Found"));
		watchRepository.delete(watch);
	}

}
