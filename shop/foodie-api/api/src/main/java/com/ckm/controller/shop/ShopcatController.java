package com.ckm.controller.shop;

import com.ckm.bo.ShopcartBO;
import com.ckm.util.JSONResult;
import com.ckm.util.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "商品购物车相关接口",tags = {"商品购物车相关接口"})
@RestController
@RequestMapping("/shopcart")
public class ShopcatController {

    @ApiOperation(value = "添加商品购物车",notes = "添加商品购物车",httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult search(
            @ApiParam(name = "userId",value = "用户编号",required = true)
            @RequestParam String userId,
            @ApiParam(name = "shopcartBO",value = "商品购物车数据",required = true)
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        try {
            if(StringUtils.isBlank(userId)){
                return JSONResult.errorMsg("");
            }
            //TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存中
            System.out.println(shopcartBO);

            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }
}
