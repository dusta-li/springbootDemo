package com.imooc.product.common;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoOutput {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer productStatus;//0正常 1下架
    private Integer categoryType;
}
