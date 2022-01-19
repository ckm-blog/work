package com.ckm.itemService.Impl;

import com.ckm.dao.*;
import com.ckm.enums.CommentLevel;
import com.ckm.itemService.ItemService;
import com.ckm.pojo.*;
import com.ckm.service.BaseService;
import com.ckm.util.PagedGridResult;
import com.ckm.vo.ShopcartVO;
import com.ckm.vo.item.CommentLeventCountVo;
import com.ckm.vo.item.ItemCommentVo;

import com.ckm.vo.item.ItemsPageVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class ItemServiceImpl extends BaseService  implements ItemService {


    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper itemsImgMapper;
    @Autowired
    private ItemsSpecMapper itemsSpecMapper;
    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsCustomMapper itemsCustomMapper;

    @Autowired
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Items queryItemById(String ItemId) {
        return itemsMapper.selectByPrimaryKey(ItemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemsImg> queryItemImageList(String ItemId) {
        Example example = new Example(Carousel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",ItemId);
        List<ItemsImg> carousels = itemsImgMapper.selectByExample(example);
        return carousels;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemsSpec> queryItemSpectList(String ItemId) {
        Example example = new Example(Carousel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",ItemId);
        return itemsSpecMapper.selectByExample(example);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ItemsParam queryItemsParam(String ItemId) {
        Example example = new Example(Carousel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",ItemId);
        return itemsParamMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public CommentLeventCountVo queryItemsCommentsCount(String ItemId) {
        Integer goodsCount = getCommentsCount(ItemId, CommentLevel.GOOD.type);
        Integer normalCounts = getCommentsCount(ItemId, CommentLevel.NORMAL.type);
        Integer badCounts = getCommentsCount(ItemId, CommentLevel.BAD.type);
        Integer totalCounts=goodsCount+normalCounts+badCounts;
        CommentLeventCountVo commentLeventCountVo = new CommentLeventCountVo();
        commentLeventCountVo.setBadCounts(badCounts);
        commentLeventCountVo.setGoodsCount(goodsCount);
        commentLeventCountVo.setNormalCounts(normalCounts);
        commentLeventCountVo.setTotalCounts(totalCounts);
        return commentLeventCountVo;
    }

    //查询商品评价分类
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer getCommentsCount(String itemId,Integer level){
        ItemsComments condition = new ItemsComments();
        condition.setItemId(itemId);
        if (level != null) {
            condition.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(condition);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult queryPageItemComments(String itemId, Integer level,
                                                     Integer page,Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId",itemId);
        map.put("level",level);
        List<ItemCommentVo> list = itemsCommentsMapperCustom.queryItemcomment(map);

        //page:第几页，pageSize：每页显示条数
        PageHelper.startPage(page,pageSize);
        //分页插件封装好的
        return setPagedGridResult(list,page);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult searchPageItem(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("sort",sort);
        List<ItemsPageVo> itemPageList = itemsCustomMapper.getItemPageList(map);

        //page:第几页，pageSize：每页显示条数
        PageHelper.startPage(page,pageSize);
        //分页插件封装好的
        return setPagedGridResult(itemPageList,page);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ShopcartVO> getShopcartList(String specIds) {
        String[] split = specIds.split(",");
        List<String> splitList = new ArrayList<>();
        Collections.addAll(splitList,split);
        return itemsCustomMapper.queryItemsBySpecIds(splitList);
    }


}
