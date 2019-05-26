package com.example.demo.controller.admin;

import com.example.demo.bean.Detailed;
import com.example.demo.bean.DetailedFeedback;
import com.example.demo.bean.RestResultModule;
import com.example.demo.dao.DetailedDao;
import com.example.demo.dao.DetailedFeedbackDao;
import com.example.demo.dao.DfeedbackDao;
import com.example.demo.dao.LanguageDao;
import com.example.demo.entity.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/*
 * FeedbackController 反馈信息
 * paddy 2018/11/30
 * */
@Controller
@RequestMapping(value = "appJson/admin")
@Component("AdminFeedbackController")
public class FeedbackController {
    private  static Logger logger = LoggerFactory.getLogger(FeedbackController.class);
    @Resource
    DfeedbackDao dfeedbackDao;
    @Resource
    DetailedFeedbackDao detailedFeedbackDao;
    @Resource
    LanguageDao languageDao;
    @Resource
    DetailedDao detailedDao;

    /* 初始化*/
    @ResponseBody
    @RequestMapping("/getFeedbackPage")
    public RestResultModule getFeedbackPage(@RequestBody Map<String,Object> map){
        RestResultModule module = new RestResultModule();
        int CurrentPage = Integer.parseInt(map.get("CurrentPage").toString());
        int PageSize = Integer.parseInt(map.get("PageSize").toString());
        long langId = Long.parseLong(map.get("langId").toString());
        long comment = Long.parseLong(map.get("comment").toString());
        long df_type = Long.parseLong(map.get("df_type").toString());
        long commentStatu = Long.parseLong(map.get("commentStatu").toString());
        String startTime = map.get("startTime").toString();
        String endTime = map.get("endTime").toString();
        //分页
        Pageable pageable = new PageRequest(CurrentPage-1,PageSize);
        Page<Feedback> feedbacks = null;
        feedbacks = dfeedbackDao.getAllByDfType(langId,comment,commentStatu,df_type,startTime,endTime,pageable);

        module.putData("feedbacks",feedbacks.getContent());
        module.putData("PageCount",feedbacks.getTotalElements());
        module.putData("languages",languageDao.findAll());
        return module;
    }

    /**
     * 按ID查询
     * @param df_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFeedbackById")
    public RestResultModule getFeedbackById(@RequestParam(name = "df_id",defaultValue = "0",required = true) long df_id){
        RestResultModule module = new RestResultModule();
       /* Feedback feedback  = dfeedbackDao.getAllById(df_id);
        module.putData("feedback",feedback);*/
        DetailedFeedback feedback = detailedFeedbackDao.getById(df_id);
        if(null != feedback){
            Detailed detailed = detailedDao.findById((long)feedback.getDlId());
            if(null != detailed){
                feedback.setLang_title(languageDao.getByTitle(detailed.getLangId()));
                feedback.setDetailed_title(detailed.getTitle());
            }
        }
        module.putData("feedback",feedback);
        return module;
    }

    /**
     * 按ID查询
     * @param df_id
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/updateFeedbackStatus")
    public RestResultModule updateFeedbackStatus(
            @RequestParam(name = "df_id",defaultValue = "0",required = true) long df_id,
            @RequestParam(name = "df_nay_status",defaultValue = "0",required = true) long df_nay_status){
        RestResultModule module = new RestResultModule();
        if(df_id > 0){
            detailedFeedbackDao.updateFeedbackStatus(df_id,df_nay_status);
        }else{
            module.setCode(500);
        }
        return module;
    }

}
