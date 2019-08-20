package com.fengchao.statistics.mapper;

import com.fengchao.statistics.model.MCityOrderamount;
import com.fengchao.statistics.model.MCityOrderamountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MCityOrderamountMapper {
    long countByExample(MCityOrderamountExample example);

    int deleteByExample(MCityOrderamountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MCityOrderamount record);

    int insertSelective(MCityOrderamount record);

    List<MCityOrderamount> selectByExample(MCityOrderamountExample example);

    MCityOrderamount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MCityOrderamount record, @Param("example") MCityOrderamountExample example);

    int updateByExample(@Param("record") MCityOrderamount record, @Param("example") MCityOrderamountExample example);

    int updateByPrimaryKeySelective(MCityOrderamount record);

    int updateByPrimaryKey(MCityOrderamount record);
}