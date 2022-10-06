package com.primer_parcial_prueba.josue.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    @Column(length = 500, nullable = false)
    private String description;
    @Column(length = 100)
    private String type_product;
    @Column(length = 1000, nullable = false)
    private double pryce;
    @Column(length = 100)
    private String name_supplier;

}
