package com.takarub.shoppingCarts.repository;

import com.takarub.shoppingCarts.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByStoreId(Integer storeId);
}
