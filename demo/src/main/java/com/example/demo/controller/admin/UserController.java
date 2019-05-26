package com.example.demo.controller.admin;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * 后台-用户UserController
 * paddy 2018/9/17
 * */
@Controller
@RequestMapping(value = "appJson/admin")
public class UserController {
    private  static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

 /*   @ResponseBody
    @RequestMapping("/userlogin")
    public boolean userlogin(HttpSession request,
                             @RequestParam(name = "loginId",required = false)String loginId,
                             @RequestParam(name = "password",required = false)String password){
        System.out.println("loginId="+loginId);
        System.out.println("password="+password);
        boolean falg  = userService.login(request,loginId,password);
        return falg;
    }*/

    @ResponseBody
    @RequestMapping(value = "/userlogin",method= RequestMethod.POST)
    public boolean userlogin(HttpSession request,@RequestBody User user){
        System.out.println(user.getLoginId());
        System.out.println(user.getPassword());
        boolean falg  = userService.login(request,user.getLoginId(),user.getPassword());
        return falg;
    }

}
