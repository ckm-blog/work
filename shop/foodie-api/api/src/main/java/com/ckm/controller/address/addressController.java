package com.ckm.controller.address;

import com.ckm.addressService.UserAddressService;
import com.ckm.bo.UserAddressBo;
import com.ckm.bo.UserBo;
import com.ckm.pojo.UserAddress;
import com.ckm.util.JSONResult;
import com.ckm.util.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "地址相关接口",tags = {"地址相关接口"})
@RestController
@RequestMapping("/address")
public class addressController {
    @Autowired
    private UserAddressService userAddressService;

    @ApiOperation(value = "获取用户收货地址",notes = "获取用户收货地址",httpMethod = "GET")
    @GetMapping("/list")
    public JSONResult list(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId
    ){
        try {
            if(StringUtils.isBlank(userId)){
                return  JSONResult.errorMsg("您还未登录！");
            }
            List<UserAddress> addressList = userAddressService.getAddressList(userId);

            return JSONResult.ok(addressList);
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }



    @ApiOperation(value = "用户收货地址添加",notes = "用户收货地址添加",httpMethod = "POST")
    @PostMapping("/add")
    public JSONResult add(
            @RequestBody UserAddressBo userAddressBo
            ){
        try {
            //地址校验
            JSONResult data = checkAddress(userAddressBo);
            if(data.getStatus()!=200){
                return JSONResult.errorMsg(data.getMsg());
            }

            userAddressService.add(userAddressBo);
            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }



    @ApiOperation(value = "收货地址设置为默认地址",notes = "收货地址设置为默认地址",httpMethod = "POST")
    @PostMapping("/setDefalut")
    public JSONResult setDefalut(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "addressId",value = "地址id",required = true)
            @RequestParam String addressId
    ){
        try {
            if(StringUtils.isBlank(userId)||StringUtils.isBlank(addressId)){
                return JSONResult.errorMsg("参数不能为空!");
            }
            //1.修改地址状态
            userAddressService.updateUserAddressToBeDefault(userId,addressId);
            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    @ApiOperation(value = "编辑收货地址",notes = "编辑收货地址",httpMethod = "POST")
    @PostMapping("/update")
    public JSONResult update(
           @RequestBody UserAddressBo userAddressBo
    ){
        try {
            if(StringUtils.isBlank(userAddressBo.getAddressId())){
                return JSONResult.errorMsg("该地址不存在！");
            }
            //地址校验
            JSONResult data = checkAddress(userAddressBo);
            if(data.getStatus()!=200){
                return JSONResult.errorMsg(data.getMsg());
            }
            userAddressService.updateUserAddress(userAddressBo);
            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    @ApiOperation(value = "删除收货地址",notes = "删除收货地址",httpMethod = "POST")
    @PostMapping("/delete")
    public JSONResult delete(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam String userId,
            @ApiParam(name = "addressId",value = "地址id",required = true)
            @RequestParam String addressId
    ){
        try {
            if(StringUtils.isBlank(userId)||StringUtils.isBlank(userId)){
                return JSONResult.errorMsg("参数不能为空！");
            }
            userAddressService.delete(userId,addressId);

            return JSONResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("服务器异常！");
        }
    }


    //校验地址格式参数
    private JSONResult checkAddress(UserAddressBo addressBo) {
        String receiver = addressBo.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return JSONResult.errorMsg("收货人不能为空!");
        }
        if (receiver.length() > 12) {
            return JSONResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBo.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return JSONResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return JSONResult.errorMsg("收货人手机号长度不正确");
        }
        //校验手机号格式
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return JSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBo.getProvince();
        String city = addressBo.getCity();
        String district = addressBo.getDistrict();
        String detail = addressBo.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return JSONResult.errorMsg("收货地址信息不能为空");
        }
        return JSONResult.ok();
    }


}
