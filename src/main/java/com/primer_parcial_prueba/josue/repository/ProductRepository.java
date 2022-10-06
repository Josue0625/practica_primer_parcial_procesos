package com.primer_parcial_prueba.josue.repository;

import com.primer_parcial_prueba.josue.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);
}
