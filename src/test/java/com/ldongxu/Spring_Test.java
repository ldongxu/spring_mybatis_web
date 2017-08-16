package com.ldongxu;

import com.ldongxu.config.AppConfig;
import com.ldongxu.mapper.UserMapper;
import com.ldongxu.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by 刘东旭 on 2017/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class Spring_Test {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        System.out.println(userMapper==null);
    }
}
