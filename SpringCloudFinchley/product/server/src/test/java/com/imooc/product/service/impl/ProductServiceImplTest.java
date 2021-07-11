package com.imooc.product.service.impl;

import com.imooc.product.dataObject.ProductInfo;
import com.imooc.product.repository.ProductInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findList() {

        List<ProductInfo> byProductIdIn = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022"));
        Assert.assertTrue(byProductIdIn.size() >0);
    }
}