package com.imooc.order.dto;

import com.imooc.order.dataObject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private String OrderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;//买家微信openid
    private BigDecimal orderAmount;
    private Integer orderStatus;//订单状态，0为新下单
    private Integer payStatus;//支付状态，0为未支付

    private List<OrderDetail> orderDetailList;
}
