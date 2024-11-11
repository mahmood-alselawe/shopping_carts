package com.takarub.shoppingCarts.repository;

import com.takarub.shoppingCarts.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
