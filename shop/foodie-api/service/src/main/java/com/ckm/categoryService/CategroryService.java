package com.ckm.categoryService;

import com.ckm.pojo.Category;
import com.ckm.vo.CategoryVo;
import com.ckm.vo.NewItemsVo;

import java.util.List;

public interface CategroryService {

    //查询一级分类
    public List<Category> queryAllRootLevelCat();

    //获取二级分类
    public List<CategoryVo> getSubCatList(Integer rootCatId);

    //获取各分类最新上架的6个商品
    public List<NewItemsVo> getSixNewItems(Integer rootCatId);

}
