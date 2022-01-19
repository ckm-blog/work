package com.ckm.dao;

import com.ckm.my.mapper.MyMapper;
import com.ckm.pojo.ItemsComments;
import com.ckm.vo.item.ItemCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom {


  public List<ItemCommentVo> queryItemcomment (@Param("params")  Map<String,Object> map);
}