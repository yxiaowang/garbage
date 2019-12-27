package com.njupt.garbage.mapper;

import com.njupt.garbage.pojo.GarbageLabel;
import com.njupt.garbage.pojo.GarbageLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GarbageLabelMapper {
    long countByExample(GarbageLabelExample example);

    int deleteByExample(GarbageLabelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GarbageLabel record);

    int insertSelective(GarbageLabel record);

    List<GarbageLabel> selectByExample(GarbageLabelExample example);

    GarbageLabel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GarbageLabel record, @Param("example") GarbageLabelExample example);

    int updateByExample(@Param("record") GarbageLabel record, @Param("example") GarbageLabelExample example);

    int updateByPrimaryKeySelective(GarbageLabel record);

    int updateByPrimaryKey(GarbageLabel record);
}