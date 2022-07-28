package com.example.product.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public Product update(Product product, Long userId) throws Exception {

        Optional<Product> productOptional = productRepository.findById(product.getId());

        if (productOptional.isPresent()) {

            if (!productOptional.get().getUserId().equals(userId))
                throw new Exception("User not allowed to update");

            return productRepository.save(product);
        } else return null;

    }

    @Transactional
    public void delete(Long productId, Long userId) throws Exception {

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {

            if (!productOptional.get().getUserId().equals(userId))
                throw new Exception("User not allowed to delete");

            productRepository.deleteById(productId);
        } else throw new NoResultException("Not Found");

    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
