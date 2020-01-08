package com.kfz.scwproject1.dao;


import java.util.List;

import com.kfz.scwproject1.bean.TProjectImages;
import com.kfz.scwproject1.bean.TProjectImagesExample;
import org.apache.ibatis.annotations.Param;

public interface TProjectImagesMapper {
    long countByExample(TProjectImagesExample example);

    int deleteByExample(TProjectImagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProjectImages record);

    int insertSelective(TProjectImages record);

    List<TProjectImages> selectByExample(TProjectImagesExample example);

    TProjectImages selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProjectImages record, @Param("example") TProjectImagesExample example);

    int updateByExample(@Param("record") TProjectImages record, @Param("example") TProjectImagesExample example);

    int updateByPrimaryKeySelective(TProjectImages record);

    int updateByPrimaryKey(TProjectImages record);
}