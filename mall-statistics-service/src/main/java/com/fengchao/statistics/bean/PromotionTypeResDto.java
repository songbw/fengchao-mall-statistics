package com.fengchao.statistics.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 */
@Setter
@Getter
public class PromotionTypeResDto {

    private Long id;

    private String typeName;

    private String typeDesc;

    private Short istatus;

    private Date createTime;

    private Date updateTime;
}