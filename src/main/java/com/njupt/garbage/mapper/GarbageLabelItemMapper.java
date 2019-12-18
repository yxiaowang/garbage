package com.njupt.garbage.mapper;

import com.njupt.garbage.pojo.GarbageLabelItem;
import com.njupt.garbage.pojo.GarbageLabelItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GarbageLabelItemMapper {
    long countByExample(GarbageLabelItemExample example);

    int deleteByExample(GarbageLabelItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(GarbageLabelItem record);

    int insertSelective(GarbageLabelItem record);

    List<GarbageLabelItem> selectByExample(GarbageLabelItemExample example);

    GarbageLabelItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GarbageLabelItem record, @Param("example") GarbageLabelItemExample example);

    int updateByExample(@Param("record") GarbageLabelItem record, @Param("example") GarbageLabelItemExample example);

    int updateByPrimaryKeySelective(GarbageLabelItem record);

    int updateByPrimaryKey(GarbageLabelItem record);
}