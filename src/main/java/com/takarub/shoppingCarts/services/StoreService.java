package com.takarub.shoppingCarts.services;
import com.takarub.shoppingCarts.model.Store;
import com.takarub.shoppingCarts.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    // Create a new store
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    // Get all stores
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // Get a store by ID
    public Optional<Store> getStoreById(Integer storeId) {
        return storeRepository.findById(storeId);
    }

    // Update an existing store
    public Store updateStore(Integer storeId, Store storeDetails) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found with id: " + storeId));
        store.setName(storeDetails.getName());
        store.setLocation(storeDetails.getLocation());
        return storeRepository.save(store);
    }

    // Delete a store
    public void deleteStore(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found with id: " + storeId));
        storeRepository.delete(store);
    }
}
