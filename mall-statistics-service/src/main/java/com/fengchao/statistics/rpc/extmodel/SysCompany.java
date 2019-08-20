package com.fengchao.statistics.rpc.extmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SysCompany {

    private Long id;

    private String name;

    private String corporationName;

    private String corporationId;

    private String licenceUrl;

    private String phone;

    private String address;

    private String industry;

    private Date createTime;

    private Date updateTime;

    private String comments;

    private Integer status;

}
