package com.takarub.shoppingCarts.services;

import com.takarub.shoppingCarts.model.Product;
import com.takarub.shoppingCarts.model.Store;
import com.takarub.shoppingCarts.repository.ProductRepository;
import com.takarub.shoppingCarts.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsServices {

    private final ProductRepository productRepository;

    private final StoreRepository storeRepository;

    public Product createProductForStore(Integer storeId, Product product) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Store not found with id: " + storeId));
        product.setStore(store);
        return productRepository.save(product);
    }

    // Get all products for a specific store
    public List<Product> getProductsByStore(Integer storeId) {
        return productRepository.findByStoreId(storeId);
    }

    // Get a specific product by store and product ID
    public Optional<Product> getProductByIdAndStore(Integer storeId, Integer productId) {
        return productRepository.findByIdAndStoreId(productId, storeId);
    }
    // Update an existing product for a specific store
    public Product updateProductForStore(Integer storeId, Integer productId, Product productDetails) {
        Product product = productRepository.findByIdAndStoreId(productId, storeId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId + " in store: " + storeId));

        // Update product details
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setTitle(productDetails.getTitle());
        product.setOutStock(productDetails.isOutStock());
        product.setSize(productDetails.getSize());

        return productRepository.save(product);
    }

    // Delete a product by store and product ID
    public void deleteProductByStore(Integer storeId, Integer productId) {
        Product product = productRepository.findByIdAndStoreId(productId, storeId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId + " in store: " + storeId));
        productRepository.delete(product);
    }








}
