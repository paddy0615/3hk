package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.DetailedDao;
import com.example.demo.dao.LanguageDao;
import com.example.demo.entity.DetailedEntity;
import com.example.demo.service.DetailedService;
import com.example.demo.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 * 前台-详情DetailedController
 * paddy 2018/9/17
 * */
@Controller()
@RequestMapping(value = "appJson")
@Component("DetailedController")
public class DetailedController {
    private  static Logger logger = LoggerFactory.getLogger(DetailedController.class);
    @Resource
    CategoryDao categoryDao;
    @Resource
    DetailedDao detailedDao;
    @Resource
    LanguageDao languageDao;
    @Resource
    private DetailedService detailedService;
    @Resource
    IpUtil ipUtil;

    @ResponseBody
    @RequestMapping("/getByDetaileds")
    public List<Detailed> getByDetaileds(
            @RequestParam(name = "lang_id",required = false,defaultValue = "0")long lang_id,
            @RequestParam(name = "cat_id",required = false,defaultValue = "0")long cat_id){
        List<Detailed> detaileds = null;
        if(lang_id == 0 || cat_id == 0){
            logger.error("---lang_id或cat_id为0");
        }else{
            detaileds = detailedService.getByDetaileds(lang_id,cat_id);
        }
        return detaileds;
    }

    /* 获取某个类别*/
    @ResponseBody
    @RequestMapping("/getByDetailed")
    public RestResultModule getByDetailed(@RequestParam(name = "dlId", defaultValue = "0", required = true) long dlId){
        RestResultModule module = new RestResultModule();
        if(dlId == 0){
            module.setCode(404);
            module.setMsg("Parameter error");
            return module;
        }
        // 获取语言集合
        List<Language> languages = languageDao.findAll();
        // 获取详情信息
        Detailed detailed = detailedDao.findById(dlId);
        if(null == detailed){
            module.setCode(404);
            module.setMsg("Detailed is null");
            return module;
        }
        List<Category> categories = categoryDao.findAllByLangIdAndStatus(detailed.getLangId(),1);
        List<E_form_type> eFormTypes = detailedService.getEformTypeByDlId(dlId);


        long dfcount = detailedService.getFeedbackCnt(dlId);

        module.setCode(200);
        module.putData("languages",languages);
        module.putData("categories",categories);
        module.putData("detailed",detailed);
        module.putData("langId",detailed.getLangId());
        module.putData("dfcount",dfcount);
        module.putData("eFormTypes",eFormTypes);

        return module;
    }

    /* index搜索，带语言*/
    @ResponseBody
    @RequestMapping("/getSearch")
    public RestResultModule getSearch(
            @RequestParam(name = "langId",required = true,defaultValue = "0")long langId,
            @RequestParam(name = "search",required = false,defaultValue = "")String search){
        RestResultModule module = new RestResultModule();
        List<Detailed> detaileds = null;
        detaileds = detailedDao.getSearch(langId,search);
        module.setCode(200);
        module.putData("detaileds",detaileds);
        return module;
    }

    /**
     * 按标签搜索 (标签数, 权重 , 热点 , 更新时间)
     * @param search 内容
     */
    @ResponseBody
    @RequestMapping("/getSearchTags")
    public RestResultModule getSearchTags(
            @RequestParam(name = "langId",required = true,defaultValue = "0")long langId,
            @RequestParam(name = "search",required = false,defaultValue = "")String search){
        RestResultModule module = new RestResultModule();
        String [] sarr = search.split(" ");
        List<String> searchs = Arrays.asList(sarr);
        List<DetailedEntity> detaileds = null;
        detaileds = detailedService.getSearchTags(langId,1,searchs);
        module.setCode(200);
        module.putData("detaileds",detaileds);
        return module;
    }

    /**
     * 前台-检查搜索结果是否存在
     * @param search 内容
     */
    @ResponseBody
    @RequestMapping("/checkSearchTags")
    public RestResultModule checkSearchTags(HttpServletRequest request,
            @RequestParam(name = "langId",required = false,defaultValue = "0")long langId,
            @RequestParam(name = "search",required = false,defaultValue = "")String search){
        RestResultModule module = new RestResultModule();
        detailedService.getNoTagsCount(request,langId,search);
        return module;
    }


    /* 首页搜索*/
    @ResponseBody
    @RequestMapping("/getSerDetaileds")
    public RestResultModule getSerDetaileds(@RequestParam(name = "serach",required = false,defaultValue = "")String serach){
        RestResultModule module = new RestResultModule();
        if(!"".equals(serach)){
            List<Detailed> detaileds = null;
            detaileds = detailedDao.findAllByStatusAndTitleContaining(1,serach);
            module.putData("detaileds",detaileds);
        }
        return module;
    }

    /* 搜索页-获取热点数据*/
    @ResponseBody
    @RequestMapping("/getHotspot")
    public RestResultModule getHotspot(@RequestParam(name = "langId",required = true,defaultValue = "0")long langId){
        RestResultModule module = new RestResultModule();
        Pageable pageable = new PageRequest(0,10);
        Page<Detailed> ds = detailedDao.getHpSearchCount1(langId,pageable);
        //List<Detailed> detaileds = null;
        //detaileds = detailedDao.getHpSearchCount();
        module.putData("detaileds",ds.getContent());
        return module;
    }

    /**
     * 详细页- 智能向导
     * @param dlId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSmartGuide")
    public RestResultModule getSmartGuide(@RequestParam(name = "dlId",required = true,defaultValue = "0")long dlId){
        RestResultModule module = new RestResultModule();
        List<DetailedEntity> detaileds = null;
        if(dlId > 0){
            detaileds = detailedService.getSmartGuide(dlId);
        }
        module.putData("detaileds",detaileds);
        return module;
    }

    /**
     * 添加反馈信息, 按IP记录
     * @param request
     * @param feedback 反馈对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addFeedback",method= RequestMethod.POST)
    public RestResultModule addFeedback(HttpServletRequest request,@RequestBody DetailedFeedback feedback){
        RestResultModule module = new RestResultModule();
        if(null == feedback && feedback.getDlId() == 0){
            module.setMessage(400,"传参有误!");
            return module;
        }
        feedback.setIp(ipUtil.getIpAddr(request));
        long dfId =  detailedService.addFeedback(feedback);
        module.putData("id",dfId);
        module.putData("type",feedback.getType());
        return module;
    }

    /**
     * 更新反馈信息, 按id
     * @param feedback 反馈对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateFeedback",method= RequestMethod.POST)
    public RestResultModule updateFeedback(HttpServletRequest request,@RequestBody DetailedFeedback feedback){
        RestResultModule module = new RestResultModule();
        if("" == feedback.getContent()){
            module.setMessage(400,"内容为空!");
            return module;
        }
        if(feedback.getId() == 0){
            feedback.setIp(ipUtil.getIpAddr(request));
            feedback.setCreateDate(new Date());
        }
        detailedService.updateFeedback(feedback);
        return module;
    }

    /**
     * 删除反馈信息, 按id
     * @param feedback 反馈对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delFeedback",method= RequestMethod.POST)
    public void delFeedback(@RequestBody DetailedFeedback feedback){
        if(null == feedback && feedback.getId() == 0){
            return;
        }
        detailedService.delFeedback(feedback);
    }

    /**
     * 新:跳转详情页,添加IP数,添加热点数
     * @param dlId 详情ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getIndexDetailed",method= RequestMethod.GET)
    public String indexDetailed(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "dlId",required = true,defaultValue = "0")long dlId)throws Exception {
        if(dlId > 0){
            // 添加IP数
            if(!detailedService.addip(request,0,dlId)){
                logger.error("---------添加IP数错误,dlId="+dlId+",IP="+ipUtil.getIpAddr(request));
            }
            // 添加热点数
            if(!detailedService.addHotspot(dlId)){
                logger.error("---------添加热点数错误,dlId="+dlId+",IP="+ipUtil.getIpAddr(request));
            }
        }
        response.sendRedirect(request.getContextPath()+"/appPage/indexDetailed?dlId="+dlId);
        return "";
    }




    /* 自定义修改后台数据,因域名变化,图片位置也变化*/
    @ResponseBody
    @RequestMapping("/xiugaishujuYuming")
    public List<Detailed> addHotspot(){
        List<Detailed> detaileds = detailedDao.findAll();
        for (Detailed d:detaileds) {
            //d.setContent(d.getContent().replace("/ueditor/jsp", "/hkexpress/ueditor/jsp"));
            //detailedDao.save(d);
        }
        return detaileds;
    }

}
