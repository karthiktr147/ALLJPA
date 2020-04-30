package com.mindtree.watchstore.service;

import java.util.List;

import com.mindtree.watchstore.entity.Store;
import com.mindtree.watchstore.exception.service.WatchStoreServiceException;

/**
 * @author M1048950
 *
 */
public interface StoreService {

	/**
	 * @param store object to save
	 * @return saved Store Object
	 * @throws WatchStoreServiceException
	 */
	Store addStore(Store store) throws WatchStoreServiceException;

	/**
	 * @return list of all stores and the watches in their store
	 * @throws WatchStoreServiceException
	 */
	List<Store> displayAllStore() throws WatchStoreServiceException;

	/**
	 * @param storeName
	 * @return Store found with given id
	 * @throws WatchStoreServiceException
	 */
	Store getStoreByName(String storeName) throws WatchStoreServiceException;

	/**
	 * @param storeId
	 * @return Store found with given id
	 * @throws WatchStoreServiceException
	 */
	Store getStoreById(long storeId) throws WatchStoreServiceException;

	/**
	 * @param storeId to delete store
	 * @throws WatchStoreServiceException
	 */
	void deleteStore(long storeId) throws WatchStoreServiceException;

}
