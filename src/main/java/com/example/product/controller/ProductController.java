package com.example.product.controller;

import com.example.product.product.Product;
import com.example.product.product.ProductService;
import com.example.product.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product) {
        try {
            productService.save(product);
        }catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Not Found");
        }

        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestHeader(name = "user_id") Long userId, @RequestBody Product product) {

        try {
            Product updatedProduct = productService.update(product,userId);

            if (updatedProduct == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(updatedProduct);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }


    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestHeader(name = "user_id") Long userId){

        if (!userService.isUserExists(userId))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(productService.findAll());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> delete(@RequestHeader(name = "user_id") Long userId, @PathVariable Long productId) {

        try {
           productService.delete(productId,userId);

            return ResponseEntity.ok("Deleted");
        }catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }


    }


}
