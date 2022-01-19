package com.ckm.test;

import com.ckm.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
public class TransTest {

    @Autowired
    private TransService transService;


//    @Test
    public void myTest(){
        try {
            transService.testPropagationTrans();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
