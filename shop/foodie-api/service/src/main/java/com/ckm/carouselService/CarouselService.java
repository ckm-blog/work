package com.ckm.carouselService;

import com.ckm.pojo.Carousel;

import java.util.List;

public interface CarouselService {

    //查询轮播图
    public List<Carousel> queryAll(Integer isShow);
}
