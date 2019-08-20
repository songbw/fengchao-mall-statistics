package com.fengchao.statistics.rpc.extmodel;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderDetailBean {

    private Integer id;
    private String openId;
    private String subOrderId;
    private String tradeNo;
    private String aoyiId;
    private String companyCustNo;
    private Integer merchantId;
    private String merchantNo;
    private String receiverName;
    private String telephone;
    private String mobile;
    private String email;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String countyId;
    private String countyName;
    private String townId;
    private String address;
    private String zip;
    private String remark;
    private String invoiceState;
    private String invoiceType;
    private String invoiceTitle;
    private String invoiceContent;
    private String payment;
    private Float servFee;
    private Float amount;
    private Integer status;
    private Integer type;
    private Date createdAt;
    private Date updatedAt;
    private String skuId;
    private Integer num;
    private BigDecimal unitPrice;
    private String image;
    private String model;
    private String name;
    private String paymentNo;
    private Date paymentAt;
    private String logisticsId;
    private String logisticsContent;
    private String outTradeNo;
    private Integer paymentAmount;
    // 商户号，充值钱包的时候没有
    private String payee ;
    // 退款金额，退款时候有
    private int refundFee;
    // 支付方式
    private String payType;
    // 订单总金额
    private int paymentTotalFee;
    // C端个人账号。 表示唯一用户
    private String payer;
    // 支付状态 10初始创建订单  1下单成功，等待支付。  2支付中，3超时未支付  4支付失败  5支付成功  11支付成功，记账也成功   12支付成功，记账失败  14退款失败，15订单已退款
    private int payStatus;
    // 1支付，2充值，3退款，4提现
    private int payOrderCategory ;
    private Integer couponId;
    private String couponCode;
    private Float couponDiscount;
    private Integer promotionId;
    private BigDecimal promotionDiscount;
    private Float saleAmount;

    /**
     * 品类
     */
    private String category;
}
