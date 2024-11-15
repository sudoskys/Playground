package com.star.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.demo.mapper.ProductMapper;
import com.star.demo.mapper.UserMapper;
import com.star.demo.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}