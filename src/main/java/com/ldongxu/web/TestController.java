package com.ldongxu.web;

import com.ldongxu.mapper.UserMapper;
import com.ldongxu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 刘东旭 on 2017/8/17.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping
    @ResponseBody
    public String test(){
        User user = new User();
        user.setName("test");
        user.setAge(10);
        userMapper.insert(user);

        return "ok";
    }
}
