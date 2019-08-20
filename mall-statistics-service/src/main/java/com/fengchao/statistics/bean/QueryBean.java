package com.fengchao.statistics.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by song on 2018-03-12.
 */
 @Data
public class QueryBean implements Serializable {
        private Integer pageNo=1;
        private Integer pageSize=10;
        private Integer status= -1;
        private String startTime;
        private String endTime;
        private String key="";
        private String openId;
        private Integer merchantId;
}
