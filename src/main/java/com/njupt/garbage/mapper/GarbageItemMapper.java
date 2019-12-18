package com.njupt.garbage.mapper;

import com.njupt.garbage.pojo.GarbageItem;
import com.njupt.garbage.pojo.GarbageItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GarbageItemMapper {
    long countByExample(GarbageItemExample example);

    int deleteByExample(GarbageItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GarbageItem record);

    int insertSelective(GarbageItem record);

    List<GarbageItem> selectByExampleWithBLOBs(GarbageItemExample example);

    List<GarbageItem> selectByExample(GarbageItemExample example);

    GarbageItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GarbageItem record, @Param("example") GarbageItemExample example);

    int updateByExampleWithBLOBs(@Param("record") GarbageItem record, @Param("example") GarbageItemExample example);

    int updateByExample(@Param("record") GarbageItem record, @Param("example") GarbageItemExample example);

    int updateByPrimaryKeySelective(GarbageItem record);

    int updateByPrimaryKeyWithBLOBs(GarbageItem record);

    int updateByPrimaryKey(GarbageItem record);
}