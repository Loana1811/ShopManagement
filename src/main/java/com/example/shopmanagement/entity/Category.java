package com.example.shopmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 20)
    private String status = "ACTIVE";
}
