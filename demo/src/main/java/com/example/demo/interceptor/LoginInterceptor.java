package com.example.demo.interceptor;

import com.example.demo.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 登录验证拦截
 *
 */
@Controller
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private  static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    /**
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        User user = (User)request.getSession().getAttribute("userSession");
        if(null == user){
            // 获取request返回页面到登录页
            //response.sendRedirect("/appPage/admin/login");
            System.out.println(request.getContextPath()+"/appPage/admin/login");     // 工程名
            response.sendRedirect(request.getContextPath()+"/appPage/admin/login");
            flag = false;
        }else{
            flag = true;
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }

}
