package com.ckm.test;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TransService {



    public void testPropagationTrans();
}
