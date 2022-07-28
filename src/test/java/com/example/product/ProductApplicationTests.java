package com.example.product;

import com.example.product.product.Product;
import com.example.product.product.ProductService;
import com.example.product.user.User;
import com.example.product.user.UserRepository;
import com.example.product.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductApplicationTests {

    private final ProductService productService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public ProductApplicationTests(ProductService productService, UserService userService, UserRepository userRepository) {
        this.productService = productService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Test
    @Order(1)
    void createProductAndUser() {
        User user = new User("testname", "testsurname");
        userService.save(user);
        assert user.getId() != null;

        Product product = new Product("test_product", BigDecimal.TEN, user.getId());
        productService.save(product);
        assert product.getId() != null;

    }

    @Test
    @Order(2)
    void deleteAllUser() {
        userRepository.deleteAll();
        assert productService.findAll().isEmpty();
    }



}
