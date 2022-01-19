package com.ckm.dao;

import com.ckm.my.mapper.MyMapper;
import com.ckm.pojo.Category;
import com.ckm.vo.CategoryVo;
import com.ckm.vo.NewItemsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    //根据id获取分类
    public List<CategoryVo> getSubCatList(Integer rootCateId);


    public List<NewItemsVo> getSixNewItems(@Param("paramsMap") Map<String,Object> map);
}