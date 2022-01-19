package com.ckm.vo.item;

import com.ckm.pojo.Items;
import com.ckm.pojo.ItemsImg;
import com.ckm.pojo.ItemsParam;
import com.ckm.pojo.ItemsSpec;

import java.util.List;

public class ItemInfoVo {
    private Items item;
    private List<ItemsImg> itemImgList ;
    private ItemsParam itemParams;
    private List<ItemsSpec> itemSpecList;

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }

    public List<ItemsImg> getItemImgList() {
        return itemImgList;
    }

    public void setItemImgList(List<ItemsImg> itemImgList) {
        this.itemImgList = itemImgList;
    }

    public ItemsParam getItemParams() {
        return itemParams;
    }

    public void setItemParams(ItemsParam itemParams) {
        this.itemParams = itemParams;
    }

    public List<ItemsSpec> getItemSpecList() {
        return itemSpecList;
    }

    public void setItemSpecList(List<ItemsSpec> itemSpecList) {
        this.itemSpecList = itemSpecList;
    }
}
