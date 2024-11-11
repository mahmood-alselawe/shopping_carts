package com.takarub.shoppingCarts.repository;

import com.takarub.shoppingCarts.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
