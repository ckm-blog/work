package com.ckm.controller;

import com.ckm.StuService.StuService;
import com.ckm.pojo.Stu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



//@Controller
@RestController
public class HelloController {

    final  static Logger logger= LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private StuService stuService;


    @GetMapping("/pay")
    public String hello(){
        logger.debug("调试");
        logger.error("错误！");
        logger.warn("警告！");
        logger.info("成功");

        return "hello word!";
    }

    @GetMapping("/getStu")
    @Transactional(propagation = Propagation.SUPPORTS)
    public Stu getStu(int id){
        return stuService.getStu(id);
    }
}
