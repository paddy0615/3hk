package com.example.demo.controller.admin;

import com.example.demo.bean.E_area_name;
import com.example.demo.bean.Eform;
import com.example.demo.bean.RestResultModule;
import com.example.demo.entity.EformEntity;
import com.example.demo.entity.Feedback;
import com.example.demo.service.EformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * admin/EformController
 * paddy 2019/4/10
 * */
@Controller
@RequestMapping(value = "appJson/admin")
@Component("AdminEformController")
public class EformController {
    private  static Logger logger = LoggerFactory.getLogger(EformController.class);

    @Resource
    EformService eformService;


    /* 初始化*/
    @ResponseBody
    @RequestMapping("/getEformPage")
    public RestResultModule getEformPage(@RequestBody Map<String,Object> map){
        RestResultModule module = new RestResultModule();
        int CurrentPage = Integer.parseInt(map.get("CurrentPage").toString());
        int PageSize = Integer.parseInt(map.get("PageSize").toString());
        long langId = Long.parseLong(map.get("langId").toString());
        long type = Long.parseLong(map.get("type").toString());
        String startTime = map.get("startTime").toString();
        String endTime = map.get("endTime").toString();
        String searchTest = map.get("searchTest").toString();
        System.out.println("searchTest="+searchTest);
        //分页
        Pageable pageable = new PageRequest(CurrentPage-1,PageSize);
        Page<EformEntity> eformEntities = null;
        eformEntities = eformService.getEformEntityPage(langId,type,startTime,endTime,searchTest,pageable);
        module.putData("eforms",eformEntities.getContent());
        module.putData("PageCount",eformEntities.getTotalElements());
        module.putData("languages",eformService.findAllLanguage());
        module.putData("eformTypes",eformService.findAllFormType());
        return module;
    }

    /**
     * 按ID查询
     * @param eId
     * @return
     */
    @ResponseBody
    @RequestMapping("/findEformById")
    public RestResultModule findEformById(@RequestParam(name = "eId",defaultValue = "0",required = true) long eId) throws Exception{
        RestResultModule module = new RestResultModule();
        Eform eform = eformService.findEformById(eId);
        String title = "";
        String certificate_Nature = "";
        if(null != eform){
            title = eformService.getMailType(eform.getType(),eform.getLangId(),null == eform.getPnr()?"":eform.getPnr());
            certificate_Nature = eformService.getCertificateTitle(eform.getEcertificatetype());
        }
        module.putData("title",title);
        module.putData("eform",eform);
        module.putData("Certificate_Nature",certificate_Nature);
        return module;
    }

}
