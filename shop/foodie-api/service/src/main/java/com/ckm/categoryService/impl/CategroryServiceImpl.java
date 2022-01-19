package com.ckm.categoryService.impl;

import com.ckm.categoryService.CategroryService;
import com.ckm.dao.CategoryMapper;
import com.ckm.dao.CategoryMapperCustom;
import com.ckm.pojo.Carousel;
import com.ckm.pojo.Category;
import com.ckm.vo.CategoryVo;
import com.ckm.vo.NewItemsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategroryServiceImpl implements CategroryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Carousel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type",1);
        List<Category> carousels = categoryMapper.selectByExample(example);
        return carousels;

    }

    @Override
    public List<CategoryVo> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }

    @Override
    public List<NewItemsVo> getSixNewItems(Integer rootCatId) {
        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId",rootCatId);
        return categoryMapperCustom.getSixNewItems(map);
    }
}
