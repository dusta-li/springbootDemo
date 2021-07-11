package com.imooc.order.dataObject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class OrderMaster {
    @Id
    private String OrderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;//买家微信openid
    private BigDecimal orderAmount;
    private Integer orderStatus;//订单状态，0为新下单
    private Integer payStatus;//支付状态，0为未支付
    private Date createTime;
    private Date updateTime;

}
