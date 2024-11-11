package com.takarub.shoppingCarts.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String title;
    private boolean isOutStock;
    private Integer size;
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

}
