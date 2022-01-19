package com.ckm.controller.user;

import com.ckm.carouselService.CarouselService;
import com.ckm.categoryService.CategroryService;
import com.ckm.enums.YesOrNo;
import com.ckm.pojo.Carousel;
import com.ckm.pojo.Category;
import com.ckm.util.JSONResult;
import com.ckm.vo.CategoryVo;
import com.ckm.vo.NewItemsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "首页展示的相关接口",tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategroryService categroryService;

    @ApiOperation(value = "获取轮播图",notes = "获取轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel(){
        try {
            List<Carousel> carousels = carouselService.queryAll(YesOrNo.YES.type);
            //请求成功
            return JSONResult.ok(carousels);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    /**
     * 获取商品一级分类
     * @return
     */
    @ApiOperation(value = "获取商品分类（一级分类）",notes = "获取商品分类（一级分类）",httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats(){
        try {
            List<Category> carousels = categroryService.queryAllRootLevelCat();
            //请求成功
            return JSONResult.ok(carousels);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    /**
     * 获取商品子分类（二级分类）
     * @return
     */
    @ApiOperation(value = " 获取商品子分类（二级分类）",notes = " 获取商品子分类（二级分类）",httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONResult subCat(
            @ApiParam(name = "rootCatId",value = "一分类Id",required = true)
            @PathVariable Integer rootCatId){
        try {
            if(rootCatId==null){
                return JSONResult.errorMsg("分类不存在！");
            }
            List<CategoryVo> subCatList = categroryService.getSubCatList(rootCatId);
            //请求成功
            return JSONResult.ok(subCatList);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    /**
     * 获取各分类下的最新6个商品
     * @return
     */
    @ApiOperation(value = " 获取各分类下的最新6个商品",notes = " 获取各分类下的最新6个商品",httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItems(
            @ApiParam(name = "rootCatId",value = "一分类Id",required = true)
            @PathVariable Integer rootCatId){
        try {
            if(rootCatId==null){
                return JSONResult.errorMsg("分类不存在！");
            }
            List<NewItemsVo> result = categroryService.getSixNewItems(rootCatId);
            //请求成功
            return JSONResult.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }

}
