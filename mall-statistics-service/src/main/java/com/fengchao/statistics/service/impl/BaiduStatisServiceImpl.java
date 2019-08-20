package com.fengchao.statistics.service.impl;

import com.fengchao.statistics.bean.PageBean;
import com.fengchao.statistics.bean.QueryBean;
import com.fengchao.statistics.bean.QueryUrlStatisBean;
import com.fengchao.statistics.db.annotation.DataSource;
import com.fengchao.statistics.db.config.DataSourceNames;
import com.fengchao.statistics.mapper.BaiduStatisMapper;
import com.fengchao.statistics.service.BaiduStatisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BaiduStatisServiceImpl implements BaiduStatisService {

    @Autowired
    private BaiduStatisMapper mapper;

    @DataSource(DataSourceNames.TWO)
    @Override
    public PageBean queryAllUrls(QueryBean queryBean) {
        HashMap params = new HashMap<>();
        int offset = PageBean.getOffset(queryBean.getPageNo(), queryBean.getPageSize());
        params.put("pageNo", offset);
        params.put("pageSize", queryBean.getPageSize());
        List<HashMap<String, Object>> result = mapper.queryAllUrls(params);
        Integer total = mapper.countAllUrls();
        return PageBean.build(new PageBean(), result, total, queryBean.getPageNo(), queryBean.getPageSize());
    }

    @DataSource(DataSourceNames.TWO)
    @Override
    public HashMap<String, Object> getTotalPVandUv() {
        return mapper.getTotalPVandUv();
    }

    @DataSource(DataSourceNames.TWO)
    @Override
    public PageBean queryStatisticsData(QueryUrlStatisBean queryBean) {
        HashMap params = new HashMap<>();
        int offset = PageBean.getOffset(queryBean.getPageNo(), queryBean.getPageSize());
        params.put("pageNo", offset);
        params.put("pageSize", queryBean.getPageSize());
        params.put("dayStart", queryBean.getStartTime());
        params.put("dayEnd", queryBean.getEndTime());
        params.put("urlKey", queryBean.getUrlKey());

        if(StringUtils.isEmpty(queryBean.getUrlKey())){
            //查询综合列表
            params.put("orderKey", StringUtils.defaultIfEmpty(queryBean.getOrderKey(), "avg_stop_time"));
            params.put("orderType", StringUtils.defaultIfEmpty(queryBean.getOrderType(), "desc"));
            List<HashMap<String, Object>> result = mapper.queryStatisticsData(params);
            Integer total = mapper.countStatisticsData(params);
            return PageBean.build(new PageBean(), result, total, queryBean.getPageNo(), queryBean.getPageSize());
        }else{
            //查询详情列表
            List<HashMap<String, Object>> result = mapper.queryDetailData(params);
            Integer total = mapper.countDetailData(params);
            return PageBean.build(new PageBean(), result, total, queryBean.getPageNo(), queryBean.getPageSize());
        }
    }
}
