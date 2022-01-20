package com.ckm.addressService.impl;

import com.ckm.addressService.UserAddressService;
import com.ckm.bo.UserAddressBo;
import com.ckm.dao.UserAddressMapper;
import com.ckm.enums.YesOrNo;
import com.ckm.pojo.UserAddress;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserAddress> getAddressList(String userId) {
        UserAddress address = new UserAddress();
        address.setUserId(userId);
        return userAddressMapper.select(address);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(UserAddressBo userAddressBo) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBo,userAddress);
        userAddress.setId("T"+sid.nextShort());
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        //1.查询用户所有的地址
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> userAddressList = userAddressMapper.select(userAddress);
        for (UserAddress u:userAddressList){
            //1.删除原来的默认地址
            if(u.getIsDefault()==YesOrNo.YES.type){
                u.setIsDefault(YesOrNo.NO.type);
                userAddressMapper.updateByPrimaryKey(u);
            }
            //2.设置默认地址
            if(u.getId().equals(addressId)){
                u.setIsDefault(YesOrNo.YES.type);
                userAddressMapper.updateByPrimaryKey(u);
            }
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserAddress(UserAddressBo userAddressBo) {
        //根据地址id查询到该地址
        UserAddress u = new UserAddress();
        u.setId(userAddressBo.getAddressId());
        u.setUserId(userAddressBo.getUserId());
        UserAddress userAddress = userAddressMapper.selectByPrimaryKey(u);
        //防止前端篡改
        userAddressBo.setUserId(userAddress.getUserId());
        userAddress.setId(userAddressBo.getAddressId());
        userAddress.setUpdatedTime(new Date());
        BeanUtils.copyProperties(userAddressBo,userAddress);
        userAddressMapper.updateByPrimaryKey(userAddress);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddressMapper.delete(userAddress);
    }
}
