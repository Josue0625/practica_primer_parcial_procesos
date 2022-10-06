package com.primer_parcial_prueba.josue.controllers;

import com.primer_parcial_prueba.josue.models.Product;
import com.primer_parcial_prueba.josue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
@Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/product/{id}")
    public ResponseEntity getProduct (@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity(product, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/product")
    public ResponseEntity createProduct (@RequestBody Product product){
        try{
            productRepository.save(product);
            return new ResponseEntity(product, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/listproducts")
    public ResponseEntity listProducts (){
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity editProduct (@PathVariable Long id, @RequestBody Product product){
        Optional<Product> productBD = productRepository.findById(id);
        if(productBD.isPresent()){
            try{
                productBD.get().setName(product.getName());
                productBD.get().setDescription(product.getDescription());
                productBD.get().setType_product(product.getType_product());
                productBD.get().setName_supplier(product.getName_supplier());
                productBD.get().setPryce(product.getPryce());
                productRepository.save(productBD.get());
            }catch(Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional<Product> productBD = productRepository.findById(id);
        if(productBD.isPresent()){
            productRepository.delete(productBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(value = "/product/name/{name}")
    public ResponseEntity listByName(@PathVariable String name){
        List<Product> productBD = productRepository.findAllByName(name);
        if(productBD.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(productBD, HttpStatus.OK);
    }
}
