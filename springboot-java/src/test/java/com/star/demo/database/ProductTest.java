package com.star.demo.database;

import com.star.demo.mapper.ProductMapper;
import com.star.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Product> userList = productMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}