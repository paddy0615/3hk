package com.example.demo.controller;

import com.example.demo.bean.Language;
import com.example.demo.bean.RestResultModule;
import com.example.demo.dao.LanguageDao;
import com.example.demo.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 * 语言LanguageController
 * paddy 2018/9/17
 * */
@Controller
@RequestMapping(value = "appJson")
public class LanguageController {
    private  static Logger logger = LoggerFactory.getLogger(LanguageController.class);

    @Resource
    LanguageDao languageDao;

    @Resource
    LanguageService languageService;


    @ResponseBody
    @RequestMapping("/getLanguageAll")
    public List<Language> getLanguageAll(){
        List<Language> languages = languageDao.findAll();
        return languages;
    }

    @ResponseBody
    @RequestMapping("/getLanguage")
    public Language getLanguage(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId){
        return languageDao.findById(langId);
    }


    @ResponseBody
    @RequestMapping(value = "/language/update",method= RequestMethod.POST)
    public void update(@RequestBody Language language){
        if(null != language){
            language.setUpdateDate(new Date());
            languageService.save(language);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/language/add")
    public void add(@RequestParam(name = "title",defaultValue = "",required = true) String title,
                    @RequestParam(name = "problem",defaultValue = " ",required = true) String problem){
        Language language = new Language();
        language.setCreateDate(new Date());
        language.setUpdateDate(new Date());
        language.setCreateUser(Long.parseLong("1"));
        language.setUpdateUser(Long.parseLong("1"));
        language.setTitle(title);
        language.setProblem(problem);
        languageService.save(language);
    }

    @ResponseBody
    @RequestMapping("/language/delete")
    public void delete(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId){
        languageService.deleteById(langId);
    }


    @ResponseBody
    @RequestMapping("/language/getSearchTitle")
    public RestResultModule getSearchTitle(@RequestParam(name = "serach",required = false,defaultValue = "")String serach){
        RestResultModule module = new RestResultModule();
        List<Language> languages = null;
        languages = languageDao.findAllByTitleContaining(serach);
        module.putData("languages",languages);
        return module;
    }






}
