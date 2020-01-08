package com.kfz.scwproject1.dao;


import java.util.List;

import com.kfz.scwproject1.bean.TReturn;
import com.kfz.scwproject1.bean.TReturnExample;
import org.apache.ibatis.annotations.Param;

public interface TReturnMapper {
    long countByExample(TReturnExample example);

    int deleteByExample(TReturnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    int insertSelective(TReturn record);

    List<TReturn> selectByExample(TReturnExample example);

    TReturn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByExample(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByPrimaryKeySelective(TReturn record);

    int updateByPrimaryKey(TReturn record);
}