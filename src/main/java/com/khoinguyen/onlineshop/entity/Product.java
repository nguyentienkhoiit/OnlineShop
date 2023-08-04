package com.khoinguyen.onlineshop.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    double price;
    int quantity;
    @Column(columnDefinition = "TEXT")
    String summary;
    @Column(columnDefinition = "TEXT")
    String description;
    @Column(columnDefinition = "TEXT")
    String imageUrl;
    String availability;
    String specification;

    @ManyToOne(cascade = CascadeType.ALL)
   	@JoinColumn(name = "category_id")
    Category category;
}
