package com.fengchao.statistics.bean;

import lombok.Data;

@Data
public class QueryUrlStatisBean extends QueryBean {
    private String urlKey;
    private String orderKey;
    private String orderType;
}
