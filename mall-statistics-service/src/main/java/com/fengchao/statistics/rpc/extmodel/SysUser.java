package com.fengchao.statistics.rpc.extmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SysUser {
    private Long id;

    private Long roleId;

    private String loginName;

    private String fullName;

    private Long companyId;

    private String email;

    private String phone;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Integer status;

}