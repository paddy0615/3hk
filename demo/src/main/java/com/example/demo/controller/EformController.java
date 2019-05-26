package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.*;
import com.example.demo.dao.E_area_nameDao;
import com.example.demo.dao.EformDao;
import com.example.demo.dao.LanguageDao;
import com.example.demo.service.EformService;
import com.example.demo.service.LanguageService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/*
 * EformController
 * paddy 2019/3/26
 * */
@Controller
@RequestMapping(value = "appJson")
public class EformController {
    private  static Logger logger = LoggerFactory.getLogger(EformController.class);

    @Resource
    EformService eformService;

    /**
     * 地区名
     * @return
     */
    @ResponseBody
    @RequestMapping("/E/getAreaNames")
    public List<E_area_name> getAreaNames(){
        return eformService.getAreaNames();
    }

    /**
     * eForm3
     * 实例化
     * @return
     */
    @ResponseBody
    @RequestMapping("/E/getEform3")
    public RestResultModule getEform3(){
        RestResultModule module = new RestResultModule();
        // 语言
        module.putData("languages",eformService.findAllLanguage());
        // 地区名
        module.putData("e_area_names",eformService.getAreaNames());
        // 证明类别
        module.putData("e_certificates",eformService.getCertificates());
        return module;
    }


    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(value = "/E/add",method= RequestMethod.POST)
    public RestResultModule add(@RequestBody Eform eform){
        RestResultModule module = new RestResultModule();
       if(null != eform){
           try{
               eform.setRandom(eformService.getRandom());
               eform.setUpdateDate(new Date());
               // 添加form结果表
               E_form_result result = new E_form_result();
               String state = "-3";
               if(null != eform.getPnr()){
                   state = eformService.getBookingAPI(eform,result);
               }else{
                   state = "0";
               }
               // 比较接口 State=0时表示"Matched"; 其它值表示"Not Matched".
               if("0".equals(state)){
                   eformService.save(eform);
                   result.setEid(eform.getId());
                   eformService.saveResult(result);
                   // 发邮件
                   Map<String, Object> valueMap = new HashMap<>();
                   valueMap.put("eform", eform);
                   valueMap.put("title", eformService.getMailType(eform.getType(),eform.getLangId(),null == eform.getPnr()?"":eform.getPnr()));
                   valueMap.put("cc", eform.getEmail());
                   valueMap.put("Certificate_Nature", eformService.getCertificateTitle(eform.getEcertificatetype()));
                   eformService.sendSimpleMail(valueMap);
                   // 发确认邮件
                   Map<String, Object> valueMapUser = new HashMap<>();
                   valueMapUser.put("title", eformService.getMailUserType(eform.getLangId().toString()));
                   valueMapUser.put("To",eform.getEmail());
                   valueMapUser.put("langId",eform.getLangId());
                   valueMapUser.put("random",eform.getRandom());
                   eformService.sendSimpleMailUser(valueMapUser);
                   // 返回成功码
                   module.putData("key",eform.getRandom());
               }else{
                   module.setCode(404);
               }
           }catch (Exception e){
               logger.error("-----------/E/add-----------"+e,eform);
               module.setCode(500);
           }
       }
       return module;
    }


    /**
     * 添加添加addTest
     */
    @ResponseBody
    @RequestMapping(value = "/E/addTest",method= RequestMethod.POST)
    public RestResultModule addTest(@RequestBody Eform eform){
        RestResultModule module = new RestResultModule();
        if(null != eform){
            try{
                eform.setRandom(eformService.getRandom());
                eform.setUpdateDate(new Date());
                // 添加form结果表
                E_form_result result = new E_form_result();
                String state = "-3";
                if(null != eform.getPnr()){
                    state = eformService.getBookingAPI(eform,result);
                }else{
                    state = "0";
                }
                // 比较接口 State=0时表示"Matched"; 其它值表示"Not Matched".
                if("0".equals(state)){
                    eformService.save(eform);
                    result.setEid(eform.getId());
                    eformService.saveResult(result);
                    // 发邮件
                    Map<String, Object> valueMap = new HashMap<>();
                    valueMap.put("eform", eform);
                    valueMap.put("title", eformService.getMailType(eform.getType(),eform.getLangId(),null == eform.getPnr()?"":eform.getPnr()));
                    valueMap.put("cc", eform.getEmail());
                    valueMap.put("Certificate_Nature", eformService.getCertificateTitle(eform.getEcertificatetype()));
                    eformService.sendSimpleMail(valueMap);
                    // 发确认邮件
                    Map<String, Object> valueMapUser = new HashMap<>();
                    valueMapUser.put("title", eformService.getMailUserType(eform.getLangId().toString()));
                    valueMapUser.put("To",eform.getEmail());
                    valueMapUser.put("langId",eform.getLangId());
                    valueMapUser.put("random",eform.getRandom());
                    eformService.sendSimpleMailUser(valueMapUser);
                    // 返回成功码
                    module.putData("key",eform.getRandom());
                }else{
                    logger.error("-----------/E/add-----------error=",eform);
                    module.setCode(404);
                }
            }catch (Exception e){
                logger.error("-----------/E/add-----------"+e,eform);
                module.setCode(500);
            }
        }
        return module;
    }

    /**
     * test
     * @return
     */
    @ResponseBody
    @RequestMapping("/test")
    public String test(Model model) throws Exception{
        System.out.println("开始");
        return "ok";
    }

}
