package com.example.demo.controller.admin;

import com.example.demo.bean.*;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.DetailedDao;
import com.example.demo.dao.LanguageDao;
import com.example.demo.dao.LibrabryEntityDao;
import com.example.demo.entity.DetailedEntity;
import com.example.demo.entity.LibrabryEntity;
import com.example.demo.entity.Tags;
import com.example.demo.service.DetailedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 * 后台-详情DetailedController
 * paddy 2018/9/17
 * */
@Controller()
@RequestMapping(value = "appJson/admin")
@Component("AdminDetailedController")
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


    /* 初始化*/
    @ResponseBody
    @RequestMapping("/getDetailedPage")
    public RestResultModule getDetailedPage(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId,
                                            @RequestParam(name = "catId",defaultValue = "0",required = true) long catId){
        RestResultModule module = new RestResultModule();
        // 获取语言集合
        List<Language> languages = languageDao.findAll();
        // 获取类别集合
        List<Category> categories = categoryDao.findByLangId(langId);
        List<Detailed> detaileds = null;
        if(categories.size() > 0 ){
            // 移除第一个（主页）
            categories.remove(0);
            // 获取详情集合--默认第一个开始
            if(catId == 0){
                detaileds = detailedDao.findAllByLangIdAndCatId(langId,categories.get(0).getId());
                catId = categories.get(0).getId();
            }else{
                detaileds = detailedDao.findAllByLangIdAndCatId(langId,catId);
            }
        }

        module.setCode(200);
        module.putData("languages",languages);
        module.putData("categories",categories);
        module.putData("detaileds",detaileds);
        module.putData("selectCatId",catId);
        return module;
    }

    /* 按固定语言ID和类别ID，查询详情集合*/
    @ResponseBody
    @RequestMapping("/getDetaileds")
    public RestResultModule getDetaileds(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId,
                                         @RequestParam(name = "catId",defaultValue = "0",required = true) long catId){
        RestResultModule module = new RestResultModule();
        List<Detailed> detaileds = null;
        if(langId == 0 || catId == 0){
            module.setMessage(404,"参数错误");
        }else{
            detaileds = detailedDao.findAllByLangIdAndCatId(langId,catId);
        }
        module.putData("detaileds",detaileds);
        return module;
    }

    /* 编辑页面*/
    @ResponseBody
    @RequestMapping("/getDetailedUpdate")
    public RestResultModule getDetailedUpdate(@RequestParam(name = "dlId",defaultValue = "0",required = true) long dlId){
        RestResultModule module = new RestResultModule();
        // 获取详情
        Detailed detailed = detailedDao.findById(dlId);
        // 获取语言
        Language language = languageDao.findById(detailed.getLangId().longValue());
        // 获取类别集合
        List<Category> categories = categoryDao.findByLangId(detailed.getLangId());
        Category category = categoryDao.findById(detailed.getCatId().longValue());
        // 移除第一个（主页）
        categories.remove(0);

        // 获取标签
        String [] tags = detailedService.getTags(dlId);

        module.setCode(200);
        module.putData("detailed",detailed);
        module.putData("language",language);
        module.putData("category",category);
        module.putData("categories",categories);
        module.putData("tags",tags);
        return module;
    }

    /* 添加页面*/
    @ResponseBody
    @RequestMapping("/getDetailedAdd")
    public RestResultModule getDetailedAdd(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId){
        RestResultModule module = new RestResultModule();
        // 获取语言
        List<Language> languages = languageDao.findAll();
        // 获取类别集合-默认第一个
        if(langId == 0){
            langId = languages.get(0).getId();
        }
        List<Category> categories = categoryDao.findByLangId(langId);
        // 移除第一个（主页）
        categories.remove(0);
        module.setCode(200);
        module.putData("languages",languages);
        module.putData("categories",categories);
        module.putData("selectCatId",categories.get(0).getId());
        return module;
    }

    /**
     * 更新
     * @param tags 公共,标签
     */
    @ResponseBody
    @RequestMapping(value = "/detailed/update",method= RequestMethod.POST)
    public void update(@RequestBody Tags tags){
        Detailed detailed = tags.getDetailed();
        if(null != detailed){
            detailed.setUpdateDate(new Date());
            detailed.setOrderTopDate(new Date());
            detailedService.save(detailed);
            // 更新标签
            if(tags.getTagsArr().length > 0){
                detailedService.saveTags(detailed.getId(),tags.getTagsArr());
            }
        }
    }

    /**
     * 添加
     * @param tags 公共,标签
     */
    @ResponseBody
    @RequestMapping(value = "/detailed/add",method= RequestMethod.POST)
    public void add(@RequestBody Tags tags){
        Detailed detailed = tags.getDetailed();
        if(null != detailed){
            detailed.setCreateDate(new Date());
            detailed.setUpdateDate(new Date());
            detailed.setCreateUser(Long.parseLong("1"));
            detailed.setUpdateUser(Long.parseLong("1"));
            detailed.setOrderTopDate(new Date());
            detailedService.save(detailed);
            // 更新标签
            if(tags.getTagsArr().length > 0){
                detailedService.saveTags(detailed.getId(),tags.getTagsArr());
            }
        }
    }

    /**
     * 2.2添加,编辑统一
     * @param tags 公共,标签
     */
    @ResponseBody
    @RequestMapping(value = "/detailed/faqThreeUpdate",method= RequestMethod.POST)
    public RestResultModule faqThreeUpdate(@RequestBody Tags tags){
        RestResultModule module = new RestResultModule();
        Detailed detailed = tags.getDetailed();
        if(null != detailed){
            detailed.setUpdateDate(new Date());
            detailed.setOrderTopDate(new Date());
            detailed.setUpdateUser(Long.parseLong("1"));
            if(null == detailed.getId()){
                detailed.setCreateDate(new Date());
                detailed.setCreateUser(Long.parseLong("1"));
            }
            detailedService.save(detailed);
            // 更新标签
            if(tags.getTagsArr().length > 0){
                detailedService.saveTags(detailed.getId(),tags.getTagsArr());
            }
            // 更新eformtype
            if(tags.getEformtypeArr().length > 0){
                detailedService.saveEformType(detailed.getId(),tags.getEformtypeArr());
            }

        }
        module.putData("dlId",detailed.getId());
        return module;
    }

    /* 是否存在某个类别下的详情集合*/
    @ResponseBody
    @RequestMapping(value = "/detailed/countDlByCatId")
    public boolean countDlByCatId(@RequestParam(name = "catId",defaultValue = "0",required = true) long catId){
        return detailedService.countByCatId(catId);
    }

    /* 删除*/
    @ResponseBody
    @RequestMapping(value = "/detailed/delete")
    public void delete(@RequestParam(name = "dlIds",defaultValue = "",required = true) String dlIds){
        String [] dlId = dlIds.split("-");
        for (String id : dlId) {
            detailedService.deleteById(Long.parseLong(id));
        }
    }

    /* 按title模糊查询,带语言,类别*/
    @ResponseBody
    @RequestMapping("/detailed/getSearchTitle")
    public RestResultModule getSearchTitle(
            @RequestParam(name = "langId",defaultValue = "0",required = true) long langId,
            @RequestParam(name = "catId",defaultValue = "0",required = true) long catId,
            @RequestParam(name = "serach",required = false,defaultValue = "")String serach){
        RestResultModule module = new RestResultModule();
        List<Detailed> detaileds = null;
        detaileds = detailedDao.getSerDateAll(langId,catId,serach);
        module.putData("detaileds",detaileds);
        return module;
    }

    /**
     * 按标签搜索
     * @param langId
     * @param catId
     * @param serach
     * @return
     */
    @ResponseBody
    @RequestMapping("/detailed/getSearchTags")
    public RestResultModule getSearchTags(
            @RequestParam(name = "langId",defaultValue = "0",required = true) long langId,
            @RequestParam(name = "catId",defaultValue = "0",required = true) long catId,
            @RequestParam(name = "serach",required = false,defaultValue = "")String serach){
        RestResultModule module = new RestResultModule();
        String [] sarr = serach.split(" ");
        List<String> searchs = Arrays.asList(sarr);
        List<Detailed> detaileds = null;
        detaileds = detailedDao.getAdminSetTags(langId,catId,searchs);
        module.putData("detaileds",detaileds);
        return module;
    }

    /* 修改状态*/
    @ResponseBody
    @RequestMapping(value = "/detailed/editStatus")
    public void editStatus(@RequestParam(name = "dlIds",defaultValue = "",required = true) String dlIds,
                           @RequestParam(name = "status",defaultValue = "0",required = true) long status){
        String [] dlId = dlIds.split("-");
        for (String id : dlId) {
            detailedService.saveStatus(Long.parseLong(id),status);
        }
    }

    /* 置顶*/
    @ResponseBody
    @RequestMapping(value = "/detailed/editTop")
    public void editTop(@RequestParam(name = "dlId",defaultValue = "0",required = true) long dlId){
       detailedService.saveTop(dlId);
    }


}
