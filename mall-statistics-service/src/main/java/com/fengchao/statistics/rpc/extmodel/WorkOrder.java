package com.fengchao.statistics.rpc.extmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author tom
 * @Date 19-8-6 上午10:28
 */
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

    private String appid;

    private String title;

    private String description;

    private String receiverId;

    private String receiverName;

    private String receiverPhone;

    private Integer typeId;

    private String outcome;

    private Date finishTime;

    private Integer urgentDegree;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String createdBy;

    private String updatedBy;
}
