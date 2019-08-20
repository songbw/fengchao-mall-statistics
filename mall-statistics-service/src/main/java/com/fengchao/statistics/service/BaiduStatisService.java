package com.fengchao.statistics.service;

import com.fengchao.statistics.bean.PageBean;
import com.fengchao.statistics.bean.QueryBean;
import com.fengchao.statistics.bean.QueryUrlStatisBean;

import java.util.HashMap;

public interface BaiduStatisService {

    PageBean queryAllUrls(QueryBean queryBean);

    HashMap<String, Object> getTotalPVandUv();

    PageBean queryStatisticsData(QueryUrlStatisBean queryBean);
}
