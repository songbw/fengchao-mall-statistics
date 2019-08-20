package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.MerchantOverview;
import com.fengchao.statistics.model.MerchantOverviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantOverviewMapper {
    long countByExample(MerchantOverviewExample example);

    int deleteByExample(MerchantOverviewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MerchantOverview record);

    int insertSelective(MerchantOverview record);

    List<MerchantOverview> selectByExample(MerchantOverviewExample example);

    MerchantOverview selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MerchantOverview record, @Param("example") MerchantOverviewExample example);

    int updateByExample(@Param("record") MerchantOverview record, @Param("example") MerchantOverviewExample example);

    int updateByPrimaryKeySelective(MerchantOverview record);

    int updateByPrimaryKey(MerchantOverview record);
}