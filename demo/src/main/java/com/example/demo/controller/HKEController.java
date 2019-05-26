package com.example.demo.controller;

import com.example.demo.bean.Monitor;
import com.example.demo.dao.MonitorDao;
import com.example.demo.util.IpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*
 * 接收HKE,url做跳转
 * paddy 2018/12/26
 * */
@Controller
@RequestMapping
public class HKEController {
    @Resource
    MonitorDao monitorDao;
    @Resource
    IpUtil ipUtil;

    /**
     * HKE请求
     * @return
     */
    @RequestMapping(value="/videofaq/page/{name}",method= RequestMethod.GET)
    public String farerules_zh_cn(HttpServletRequest request,@PathVariable("name") String name){
        // 默认英文;
        long langId = 6;
        if(("farerules_zh_hk.ftl").equals(name)){
            // 香港
            langId = 1;
        }else if(("farerules_zh_cn.ftl").equals(name)){
            // 简体
            langId = 2;
        }else if(("farerules_zh_tw.ftl").equals(name)){
            // 台湾
            langId = 3;
        }else if(("farerules_ja.ftl").equals(name)){
            // 日本
            langId = 4;
        }else if(("farerules_ko.ftl").equals(name)){
            // 韩文
            langId = 5;
        }
        System.out.println(name);
        // 添加记录
        Monitor monitor = new Monitor();
        String ip = ipUtil.getIpAddr(request);
        monitor.setClientip(ip);
        monitor.setCreateDate(new Date());
        monitor.setLangId(langId);
        monitor.setCatId(Long.valueOf(0));
        monitor.setDlId(Long.valueOf(0));
        monitorDao.save(monitor);
        return "redirect:/appPage/index?langId="+langId;
    }


}
