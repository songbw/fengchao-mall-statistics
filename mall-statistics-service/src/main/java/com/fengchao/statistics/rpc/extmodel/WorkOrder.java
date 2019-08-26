package com.fengchao.statistics.rpc.extmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class WorkOrder {
    private Long id;

    private Long merchantId;

    private String orderId;

    private Integer orderGoodsNum;

    private String tradeNo;

    private Integer returnedNum;

    private Float salePrice;

    private String refundNo;

    private Float refundAmount;

    private Float guanaitongRefundAmount;

    private String guanaitongTradeNo;

    private String iAppId;

    private String tAppId;

    private String title;

    private String description;

    private String receiverId;

    private String receiverName;

    private String receiverPhone;

    private Integer typeId;

    private Integer status;

    private Date createTime;

    private Date updateTime;


}