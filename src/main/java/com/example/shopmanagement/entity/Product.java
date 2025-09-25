package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    private Integer stock = 0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 20)
    private String status = "ACTIVE";
}
