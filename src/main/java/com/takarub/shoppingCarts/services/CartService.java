package com.takarub.shoppingCarts.services;

import com.takarub.shoppingCarts.model.Cart;
import com.takarub.shoppingCarts.model.CartItem;
import com.takarub.shoppingCarts.model.Store;
import com.takarub.shoppingCarts.repository.CartRepository;
import com.takarub.shoppingCarts.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final StoreRepository storeRepository;

    // Create a new cart for a specific store
    public Cart createCartForStore(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found with id: " + storeId));
        Cart cart = new Cart();
        cart.setStore(store);
        return cartRepository.save(cart);
    }

    // Get all carts for a specific store
    public List<Cart> getCartsByStore(Integer storeId) {
        return cartRepository.findByStoreId(storeId);
    }

    // Get a specific cart by ID
    public Optional<Cart> getCartById(Integer cartId) {
        return cartRepository.findById(cartId);
    }

    // Clear all items from a specific cart
    public void clearCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
        cart.clear();
        cartRepository.save(cart);
    }

    // Delete a cart
    public void deleteCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
        cartRepository.delete(cart);
    }
}
