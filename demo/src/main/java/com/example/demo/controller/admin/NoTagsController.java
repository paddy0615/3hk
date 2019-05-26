package com.example.demo.controller.admin;

import com.example.demo.bean.DetailedNoTags;
import com.example.demo.bean.RestResultModule;
import com.example.demo.dao.DetailedNoTagsDao;
import com.example.demo.dao.LanguageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/*
 * NoTagsController 前台-搜索无结果类
 * paddy 2018/12/19
 * */
@Controller
@RequestMapping(value = "appJson/admin")
@Component("AdminNoTagsController")
public class NoTagsController {
    private  static Logger logger = LoggerFactory.getLogger(NoTagsController.class);
    @Resource
    DetailedNoTagsDao noTagsDao;
    @Resource
    LanguageDao languageDao;

    /* 初始化*/
    @ResponseBody
    @RequestMapping("/getNoTagsPage")
    public RestResultModule getNoTagsPage(
            @RequestParam(name = "langId",defaultValue = "0",required = true) long langId,
            @RequestParam(name = "CurrentPage",defaultValue = "1",required = true) int CurrentPage,
            @RequestParam(name = "PageSize",defaultValue = "10",required = true) int PageSize,
            @RequestParam(name = "startTime",defaultValue = "",required = true) String startTime,
            @RequestParam(name = "endTime",defaultValue = "",required = true) String endTime){

        RestResultModule module = new RestResultModule();
        System.out.println(CurrentPage+","+PageSize);
   /*   分页
        Pageable pageable = new PageRequest(CurrentPage-1,PageSize);
        Page<Feedback> feedbacks = null;
        if(df_type > 0){
            feedbacks = dfeedbackDao.getAllByDfType(df_type,pageable);
        }else{
            feedbacks = dfeedbackDao.getAllBy(pageable);
        }
        module.putData("feedbacks",feedbacks.getContent());
        module.putData("PageCount",feedbacks.getTotalElements());*/

        // 原查询 , 后可删jpa
        List<DetailedNoTags> noTags = noTagsDao.getAllByDNTs(langId,startTime,endTime);
        for (DetailedNoTags d:noTags) {
            d.setLang_title(languageDao.getByTitle(d.getLangId()));
        }

        module.putData("notags",noTags);
        return module;
    }


}
