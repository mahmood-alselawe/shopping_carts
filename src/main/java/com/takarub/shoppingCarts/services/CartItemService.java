package com.takarub.shoppingCarts.services;

import com.takarub.shoppingCarts.model.Cart;
import com.takarub.shoppingCarts.model.CartItem;
import com.takarub.shoppingCarts.model.Product;
import com.takarub.shoppingCarts.repository.CartItemRepository;
import com.takarub.shoppingCarts.repository.CartRepository;
import com.takarub.shoppingCarts.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    // Add an item to a cart
    public CartItem addCartItem(Integer cartId, Integer productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        cart.addItem(cartItem);
        cartRepository.save(cart); // Save the updated cart with the new item
        return cartItemRepository.save(cartItem);
    }

    // Remove an item from a cart
    public void removeCartItem(Integer cartId, Integer cartItemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with id: " + cartId));
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found with id: " + cartItemId));

        cart.removeItem(cartItem);
        cartItemRepository.delete(cartItem);
    }

    // Update the quantity of an item in the cart
    public CartItem updateCartItemQuantity(Integer cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("CartItem not found with id: " + cartItemId));
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }
}
