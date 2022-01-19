package com.ckm.StuService.impl;

import com.ckm.StuService.StuService;
import com.ckm.dao.StuMapper;
import com.ckm.pojo.Stu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuDao;


    @Override
    public Stu getStu(int id) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stuDao.selectByPrimaryKey(id);
    }

    @Override
    public void SaveParent() {
        Stu stu = new Stu();
        stu.setId(1);
        stu.setAge(18);
        stu.setName("父类");
        stuDao.insert(stu);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void saveChild() {
       addChild();
       SaveParent();
        int i=1/0;
    }

    public void addChild(){
        Stu stu = new Stu();
        stu.setId(2);
        stu.setAge(17);
        stu.setName("子类");
        stuDao.insert(stu);

    }

}
