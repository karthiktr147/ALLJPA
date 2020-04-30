package com.mindtree.watchstore.service;

import com.mindtree.watchstore.entity.Watch;
import com.mindtree.watchstore.exception.service.WatchStoreServiceException;

public interface WatchService {

	/**
	 * @param watch   to be added
	 * @param storeId to add the store to
	 * @return the saved watch
	 * @throws WatchStoreServiceException
	 */
	Watch addWatch(Watch watch, long storeId) throws WatchStoreServiceException;

	/**
	 * @param watchId to update the price of
	 * @param price   to update watch with
	 * @return updated watch details
	 * @throws WatchStoreServiceException
	 */
	Watch updateWatchPrice(long watchId, double price) throws WatchStoreServiceException;

	/**
	 * @param watchId to delete watch
	 * @throws WatchStoreServiceException
	 */
	void deleteWatch(long watchId) throws WatchStoreServiceException;

}
