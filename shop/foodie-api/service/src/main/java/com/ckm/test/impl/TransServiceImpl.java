package com.ckm.test.impl;

import com.ckm.StuService.StuService;
import com.ckm.test.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransServiceImpl implements TransService {

    @Autowired
    private StuService stuService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testPropagationTrans() {


            stuService.SaveParent();

            try {
                stuService.saveChild();
            }catch (Exception e){
                e.printStackTrace();
            }



    }


}
