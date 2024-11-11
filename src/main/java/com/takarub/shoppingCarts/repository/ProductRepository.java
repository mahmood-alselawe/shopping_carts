package com.takarub.shoppingCarts.repository;

import com.takarub.shoppingCarts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findByStoreId(Integer storeId);

    Optional<Product> findByIdAndStoreId(Integer productId, Integer storeId);
}
