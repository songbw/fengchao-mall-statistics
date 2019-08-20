package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.MStatisticUser;
import com.fengchao.statistics.model.MStatisticUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MStatisticUserMapper {
    long countByExample(MStatisticUserExample example);

    int deleteByExample(MStatisticUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MStatisticUser record);

    int insertSelective(MStatisticUser record);

    List<MStatisticUser> selectByExample(MStatisticUserExample example);

    MStatisticUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MStatisticUser record, @Param("example") MStatisticUserExample example);

    int updateByExample(@Param("record") MStatisticUser record, @Param("example") MStatisticUserExample example);

    int updateByPrimaryKeySelective(MStatisticUser record);

    int updateByPrimaryKey(MStatisticUser record);
}