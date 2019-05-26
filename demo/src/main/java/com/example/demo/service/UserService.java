package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("userService")
public class UserService {
    @Resource
    private UserMapper userMapper;

    public boolean login(HttpSession session, String loginId, String password) {
        User user = userMapper.login(loginId,password);
        if (user != null){
            session.setAttribute("userSession",user);
            return true;
        }else{
            return false;
        }

    }

}
