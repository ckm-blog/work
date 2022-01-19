package com.ckm.dao;

import com.ckm.my.mapper.MyMapper;
import com.ckm.pojo.Items;
import com.ckm.vo.ShopcartVO;
import com.ckm.vo.item.ItemsPageVo;
import org.apache.commons.lang3.text.StrTokenizer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCustomMapper extends MyMapper<Items> {

    public List<ItemsPageVo> getItemPageList(@Param("paramMap")Map<String,Object> map);

    public List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList")List<String> specIds);
}