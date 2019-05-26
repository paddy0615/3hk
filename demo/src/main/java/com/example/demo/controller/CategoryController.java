package com.example.demo.controller;

import com.example.demo.bean.Category;
import com.example.demo.bean.Detailed;
import com.example.demo.bean.Language;
import com.example.demo.bean.RestResultModule;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.DetailedDao;
import com.example.demo.dao.LanguageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/*
 * 前台-类别CategoryController
 * paddy 2018/9/17
* */
@Controller()
@RequestMapping(value = "appJson")
@Component("CategoryController")
public class CategoryController {
    private  static Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Resource
    CategoryDao categoryDao;
    @Resource
    DetailedDao detailedDao;
    @Resource
    LanguageDao languageDao;

    @ResponseBody
    @RequestMapping("/getCategoryAll")
    public List<Category> getCategorieAll(){
        List<Category> categories = categoryDao.findAll();
        return categories;
    }

    /* 类别页面*/
    @ResponseBody
    @RequestMapping("/getIndex")
    public RestResultModule getIndex(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId,
                                     @RequestParam(name = "catId",defaultValue = "0",required = true) long catId){
        RestResultModule module = new RestResultModule();
        if(langId == 0){
            module.setCode(404);
            module.setMsg("Parameter error");
            return module;
        }
        // 获取语言集合
        List<Language> languages = languageDao.findAll();
        // 获取类别集合,发布状态
        List<Category> categories = categoryDao.findAllByLangIdAndStatus(langId,1);
        // 获取详情集合，categories大于1(除首页)，为了获取默认第二个类型的详情集合
        List<Detailed> detaileds = null;
        if(catId == 0 && categories.size()>1){
            detaileds = detailedDao.findAllByLangIdAndCatIdAndStatus(langId,categories.get(1).getId(),1);
        }else{
            detaileds = detailedDao.findAllByLangIdAndCatIdAndStatus(langId,catId,1);
        }
        module.setCode(200);
        module.putData("languages",languages);
        module.putData("categories",categories);
        module.putData("detaileds",detaileds);
        module.putData("langId",langId);
        return module;
    }

}
