package com.ckm.addressService;

import com.ckm.bo.UserAddressBo;
import com.ckm.pojo.UserAddress;
import org.apache.tomcat.jni.Address;

import java.util.List;

public interface UserAddressService {

    /**
     * 根据用户id查询收获地址
     * @param userId
     * @return
     */
     public List<UserAddress> getAddressList(String userId);

    /**
     * 添加用户收货地址
     * @param userAddressBo
     */
     public void add(UserAddressBo userAddressBo);

    /**
     * 修改收货地址状态为默认
     * @param userId
     * @param addressId
     */
     public void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 修改收货地址
     * @param userAddressBo
     */
    public void updateUserAddress(UserAddressBo userAddressBo);

    /**
     * 删除收货地址
     * @param userId
     * @param addressId
     */
    public void delete(String userId, String addressId);
}
