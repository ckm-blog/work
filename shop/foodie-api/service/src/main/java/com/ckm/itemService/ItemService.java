package com.ckm.itemService;

import com.ckm.pojo.*;
import com.ckm.util.PagedGridResult;
import com.ckm.vo.ShopcartVO;
import com.ckm.vo.item.CommentLeventCountVo;

import java.util.List;

/**
 * 商品service
 */
public interface ItemService {

    /**
     * 根据id查询商品
     * @param ItemId
     * @return
     */
    public Items queryItemById(String ItemId);

    /**
     * 根据商品id查询商品图片
     * @param ItemId
     * @return
     */
    public List<ItemsImg> queryItemImageList(String ItemId);


    /**
     * 根据商品id查询商品规格
     * @param ItemId
     * @return
     */
    public List<ItemsSpec> queryItemSpectList(String ItemId);


    /**
     * 根据商品id查询商品属性
     * @param ItemId
     * @return
     */
    public ItemsParam queryItemsParam(String ItemId);


    /**
     * 查询商品评价的数量
     * @param ItemId
     * @return
     */
    public CommentLeventCountVo queryItemsCommentsCount(String ItemId);

    /**
     * 分页查询商品评价列表
     * page:页码
     * page:条数
     * @param itemId
     * @return
     */
    public PagedGridResult queryPageItemComments(String itemId, Integer level,
                                                 Integer page, Integer pageSize);


    /**
     * 搜索商品
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
   public PagedGridResult searchPageItem(String keywords, String sort,
                                     Integer page, Integer pageSize);


    /**
     * 获取商品购物车相关数据
     * @param specIds
     * @return
     */
   public List<ShopcartVO> getShopcartList(String specIds);
}
