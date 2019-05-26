package com.example.demo.controller.admin;

import com.example.demo.bean.*;
import com.example.demo.dao.*;
import com.example.demo.entity.LibrabryEntity;
import com.example.demo.service.DetailedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
 * 后台-新:重新整理FAQ逻辑 , 有个父级
 * paddy 2018/12/19
 * */
@Controller()
@RequestMapping(value = "appJson/admin")
@Component("AdminLibrabryController")
public class LibrabryController {
    private  static Logger logger = LoggerFactory.getLogger(LibrabryController.class);
    @Resource
    LanguageDao languageDao;
    @Resource
    DetailedDao detailedDao;
    @Resource
    private DetailedService detailedService;
    @Resource
    LibrabryEntityDao librabryEntityDao;
    @Resource
    LibrabryDao librabryDao;
    @Resource
    E_form_typeDao e_form_typeDao;


    /**
     * 获取全部,faq父级
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLibrabrys")
    public RestResultModule getLibrabrys(){
        RestResultModule module = new RestResultModule();
        List<Librabry> librabries = detailedService.getLibrabrys();
        module.putData("librabries",librabries);
        return module;
    }

    /**
     * 初始化数据;获取faq父级和语言全部
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLibrabryInfo")
    public RestResultModule getLibrabryInfo(){
        RestResultModule module = new RestResultModule();
        module.putData("librabries",detailedService.getLibrabrys());
        module.putData("languages",languageDao.findAll());
        //module.putData("eFormTypes",e_form_typeDao.findAll());
        module.putData("eFormTypes",e_form_typeDao.getAllByDlIdtest());

        return module;
    }

    /**
     * 按父级,语言查询所有
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLibrabryPage")
    public RestResultModule getLibrabryPage(@RequestBody Map<String,Object> map){
        RestResultModule module = new RestResultModule();
        int CurrentPage = Integer.parseInt(map.get("CurrentPage").toString());
        int PageSize = Integer.parseInt(map.get("PageSize").toString());
        long fl_id = Long.parseLong(map.get("fl_id").toString());
        long langId = Long.parseLong(map.get("langId").toString());
        long dl_status = Long.parseLong(map.get("dl_status").toString());
        //分页
        Pageable pageable = new PageRequest(CurrentPage-1,PageSize);
        Page<LibrabryEntity> detaileds = null;
        detaileds = librabryEntityDao.getLibrabryEntity(fl_id,langId,dl_status,pageable);
        module.putData("detaileds",detaileds.getContent());
        module.putData("PageCount",detaileds.getTotalElements());
        return module;
    }

    /* 2.2faq librabry编辑页面,按ID获取信息*/
    @ResponseBody
    @RequestMapping("/getFaqOneEdit1")
    public RestResultModule getFaqOneEdit1(
            @RequestParam(name = "fl_id",defaultValue = "0",required = true) long fl_id){
        RestResultModule module = new RestResultModule();
        module.putData("librabries",librabryDao.findById(fl_id));
        return module;
    }
    /* 2.2faq librabry编辑页面,按ID获取信息*/
    @ResponseBody
    @RequestMapping("/faqOne/faqOneUpdate")
    public RestResultModule faqOneUpdate(@RequestBody Librabry librabry){
        RestResultModule module = new RestResultModule();
        if(null == librabry.getId()){
            librabry.setCreateDate(new Date());
        }
        librabry.setUpdatedate(new Date());
        librabryDao.save(librabry);
        return module;
    }



    /* 2.2faq编辑页面,按ID获取信息*/
    @ResponseBody
    @RequestMapping("/getFaqThreeEdit1")
    public RestResultModule getFaqThreeEdit1(
            @RequestParam(name = "dlId",defaultValue = "0",required = true) long dlId){
        RestResultModule module = new RestResultModule();
        // 获取详情
        Detailed detailed = null;
        String [] tags = new String[]{};
        List<DeFormTypeRelation> deFormType = null;
        if(dlId > 0){
            detailed = detailedDao.findById(dlId);
            // 获取标签
            tags = detailedService.getTags(dlId);
            if(null != detailed && detailed.getFlId() > 0){
                detailed.setFlTitle(librabryDao.getByTitle(detailed.getFlId()));
            }
            deFormType = detailedService.getDeFormTypeRelation(dlId);

        }

        module.putData("detailed",detailed);
        module.putData("tags",tags);
        module.putData("deFormType",deFormType);
        return module;
    }

    /* 2.2faq编辑页面,下拉获取信息*/
    @ResponseBody
    @RequestMapping("/getFaqThreeEdit2")
    public RestResultModule getFaqThreeEdit2(
            @RequestParam(name = "flId",defaultValue = "0",required = true) long flId,
            @RequestParam(name = "langId",defaultValue = "0",required = true) long langId){
        RestResultModule module = new RestResultModule();
        // 获取详情
        Detailed detailed = detailedDao.getByFlIdAndLangId(flId,langId);;
        String [] tags = new String[]{};
        List<DeFormTypeRelation> deFormType = null;
        if(null != detailed){
            // 获取标签
            tags = detailedService.getTags(detailed.getId());
            detailed.setFlTitle(librabryDao.getByTitle(detailed.getFlId()));
            deFormType = detailedService.getDeFormTypeRelation(detailed.getId());
        }
        module.putData("detailed",detailed);
        module.putData("tags",tags);
        module.putData("deFormType",deFormType);
        return module;
    }


    /**
     * 2.2版;新按标签搜索(标签数, 权重 , 热点 , 更新时间)
     * @param search 内容
     */
    @ResponseBody
    @RequestMapping("/getSearchTagsNew")
    public RestResultModule getSearchTagsNew(
            @RequestParam(name = "search",required = false,defaultValue = "")String search){
        RestResultModule module = new RestResultModule();
        String [] sarr = search.split(" ");
        List<String> searchs = Arrays.asList(sarr);
        List<LibrabryEntity> detaileds = null;
        detaileds = librabryEntityDao.getSearchTagsNew(0,searchs);
        module.putData("detaileds",detaileds);
        return module;
    }


}
