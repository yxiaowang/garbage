package com.njupt.garbage.mapper;

import com.njupt.garbage.pojo.GarbageCategory;
import com.njupt.garbage.pojo.GarbageCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GarbageCategoryMapper {
    long countByExample(GarbageCategoryExample example);

    int deleteByExample(GarbageCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GarbageCategory record);

    int insertSelective(GarbageCategory record);

    List<GarbageCategory> selectByExample(GarbageCategoryExample example);

    GarbageCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GarbageCategory record, @Param("example") GarbageCategoryExample example);

    int updateByExample(@Param("record") GarbageCategory record, @Param("example") GarbageCategoryExample example);

    int updateByPrimaryKeySelective(GarbageCategory record);

    int updateByPrimaryKey(GarbageCategory record);
}