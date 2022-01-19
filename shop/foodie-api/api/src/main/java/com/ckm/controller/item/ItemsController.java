package com.ckm.controller.item;

import com.ckm.controller.BaseController;
import com.ckm.itemService.ItemService;
import com.ckm.pojo.*;
import com.ckm.util.JSONResult;
import com.ckm.util.PagedGridResult;
import com.ckm.vo.ShopcartVO;
import com.ckm.vo.item.ItemInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品信息展示的相关接口",tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("/items")
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "获取商品详情",notes = "获取商品详情",httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONResult carousel(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @PathVariable String itemId){
        try {
          if(StringUtils.isBlank(itemId)){
              return JSONResult.errorMsg(null);
          }
            Items items = itemService.queryItemById(itemId);
            List<ItemsImg> itemsImgs = itemService.queryItemImageList(itemId);
            ItemsParam itemsParam = itemService.queryItemsParam(itemId);
            List<ItemsSpec> itemsSpecs = itemService.queryItemSpectList(itemId);

            ItemInfoVo itemInfoVo = new ItemInfoVo();
            itemInfoVo.setItem(items);
            itemInfoVo.setItemImgList(itemsImgs);
            itemInfoVo.setItemParams(itemsParam);
            itemInfoVo.setItemSpecList(itemsSpecs);
            //请求成功
            return JSONResult.ok(itemInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    @ApiOperation(value = "获取商品评论条数",notes = "获取商品评论条数",httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JSONResult commentLevel(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId){
        try {
            if(StringUtils.isBlank(itemId)){
                return JSONResult.errorMsg(null);
            }
            //请求成功
            return JSONResult.ok(itemService.queryItemsCommentsCount(itemId));
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }

    @ApiOperation(value = "获取商品评论",notes = "获取商品评论",httpMethod = "GET")
    @GetMapping("/comments")
    public JSONResult comments(
            @ApiParam(name = "itemId",value = "商品id",required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level",value = "评价等级",required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page",value = "页码",required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "每页记录数",required = false)
            @RequestParam Integer pageSize
    ){
        try {
            if(StringUtils.isBlank(itemId)){
                return JSONResult.errorMsg(null);
            }
            if(page==null){
                page=1;
            }
            if(pageSize==null){
                pageSize=COMMENT_PAGE_SIZE;
            }
            PagedGridResult pagedGridResult = itemService.queryPageItemComments(itemId, level,
                    page, pageSize);
            //请求成功
            return JSONResult.ok(pagedGridResult);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }



    @ApiOperation(value = "商品搜索查询",notes = "商品搜索查询",httpMethod = "GET")
    @GetMapping("/search")
    public JSONResult search(
            @ApiParam(name = "keywords",value = "keywords",required = false)
            @RequestParam String keywords,
            @ApiParam(name = "sort",value = "sort",required = false)
            @RequestParam String sort,
            @ApiParam(name = "page",value = "页码",required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "每页记录数",required = false)
            @RequestParam Integer pageSize
    ){
        try {

            if(page==null){
                page=1;
            }
            if(pageSize==null){
                pageSize=COMMENT_PAGE_SIZE;
            }
            PagedGridResult pagedGridResult = itemService.searchPageItem(keywords, sort,
                    page, pageSize);
            //请求成功
            return JSONResult.ok(pagedGridResult);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    @ApiOperation(value = "根据商品规格ids查询购物车中的最新的数据",notes = "根据商品规格ids查询购物车中的最新的数据",httpMethod = "GET")
    @GetMapping("/refresh")
    public JSONResult refrce(
            @ApiParam(name = "itemSpecIds",value = "拼接规格的ids",required = false,example = "1001,1003,1005")
            @RequestParam String itemSpecIds
    ){
        try {
            if(StringUtils.isBlank(itemSpecIds)){
                return JSONResult.ok();
            }
            List<ShopcartVO> shopcartList = itemService.getShopcartList(itemSpecIds);

            return JSONResult.ok(shopcartList);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    @ApiOperation(value = "从购物车中删除商品",notes = "从购物车中删除商品",httpMethod = "GET")
    @GetMapping("/del")
    public JSONResult del(
            @ApiParam(name = "itemSpecId",value = "商品规格的ids",required = true)
            @RequestParam String itemSpecId,
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId
    ){
        try {
            if(StringUtils.isBlank(itemSpecId)||StringUtils.isBlank(userId)){
                return JSONResult.errorMsg("参数不能为空！");
            }
            //TODO 用户在页面删除购物车的商品数据，如果此时用户已经登录，则需要删除后端的购物车商品

            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }

}
