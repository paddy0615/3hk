package com.example.demo.controller.admin;

import com.example.demo.bean.Category;
import com.example.demo.bean.Language;
import com.example.demo.bean.RestResultModule;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.LanguageDao;
import com.example.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
 * 后台-类别AdminCategoryController
 * paddy 2018/9/17
 * */
@Controller
@RequestMapping(value = "appJson/admin")
@Component("AdminCategoryController")
public class CategoryController {
    private  static Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Resource
    CategoryService categoryService;
    @Resource
    CategoryDao categoryDao;
    @Resource
    LanguageDao languageDao;

    /* 初始化*/
    @ResponseBody
    @RequestMapping("/getCategoryPage")
    public RestResultModule getCategoryPage(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId){
        RestResultModule module = new RestResultModule();
        // 获取语言集合
        List<Language> languages = languageDao.findAll();
        // 获取类别集合--获取第一个（默认）
        //List<Category> categories = categoryDao.findAllByLangIdOrderByOrdertopdateDesc(langId);
        List<Category> categories = categoryDao.findByLangId(langId);
        module.setCode(200);
        module.putData("languages",languages);
        module.putData("categories",categories);
        return module;
    }

    /* 按语言ID查询类别集合*/
    @ResponseBody
    @RequestMapping("/getCategoryByLangId")
    public RestResultModule getCategoryByLangId(@RequestParam(name = "removeIndex",defaultValue = "0",required = true) long removeIndex,
            @RequestParam(name = "langId",defaultValue = "0",required = true) long langId){
        RestResultModule module = new RestResultModule();
        List<Category> categories = categoryDao.findByLangId(langId);
        if(removeIndex > 0){
            categories.remove(0);
        }
        module.setCode(200);
        module.putData("categories",categories);
        module.putData("selectCatId",categories.get(0).getId());
        return module;
    }

    /* 获取单个对象*/
    @ResponseBody
    @RequestMapping("/getCategory")
    public RestResultModule getCategory(@RequestParam(name = "catId",defaultValue = "0",required = true) long catId){
        RestResultModule module = new RestResultModule();
        Category categorie = categoryDao.findById(catId);
        Language language = languageDao.findById(categorie.getLangId().longValue());
        module.setCode(200);
        module.putData("categorie",categorie);
        module.putData("language",language);
        return module;
    }

    /* 修改*/
    @ResponseBody
    @RequestMapping(value = "/category/update",method= RequestMethod.POST)
    public void update(@RequestBody Category category){
        if(null != category){
            category.setUpdateDate(new Date());
            categoryService.save(category);
        }
    }

    /* 添加*/
    @ResponseBody
    @RequestMapping(value = "/category/add")
    public void add(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId,
                    @RequestParam(name = "categorieTitle",defaultValue = "",required = true) String categorieTitle){
        Category category = new Category();
        category.setLangId(langId);
        category.setTitle(categorieTitle);
        category.setCreateDate(new Date());
        category.setUpdateDate(new Date());
        category.setCreateUser(Long.parseLong("1"));
        category.setUpdateUser(Long.parseLong("1"));
        categoryService.save(category);
    }

    /* 查询是否有子集合*/
    @ResponseBody
    @RequestMapping(value = "/category/countCatByLangId")
    public boolean countCatByLangId(@RequestParam(name = "langId",defaultValue = "0",required = true) long langId){
        return categoryService.countCatByCatId(langId);
    }

    /* 删除*/
    @ResponseBody
    @RequestMapping(value = "/category/delete")
    public void delete(@RequestParam(name = "catIds",defaultValue = "",required = true) String catIds){
        String [] catId = catIds.split("-");
        for (String id : catId) {
            categoryService.deleteById(Long.parseLong(id));
        }
    }

    /* 按title模糊查询,带语言*/
    @ResponseBody
    @RequestMapping("/category/getSearchTitle")
    public RestResultModule getSearchTitle(
            @RequestParam(name = "langId",required = true,defaultValue = "0")long langId,
            @RequestParam(name = "serach",required = false,defaultValue = "")String serach){
        RestResultModule module = new RestResultModule();
        List<Category> categories = null;
        categories = categoryDao.findAllByLangIdAndTitleContaining(langId,serach);
        module.putData("categories",categories);
        return module;
    }

    /* 修改状态*/
    @ResponseBody
    @RequestMapping(value = "/category/editStatus")
    public void editStatus(@RequestParam(name = "catIds",defaultValue = "",required = true) String catIds,
                    @RequestParam(name = "status",defaultValue = "0",required = true) long status){
        String [] catId = catIds.split("-");
        for (String id : catId) {
            categoryService.saveStatus(Long.parseLong(id),status);
        }
    }

    /* 置顶*/
    @ResponseBody
    @RequestMapping(value = "/category/editTop")
    public void editTop(@RequestParam(name = "firstId",defaultValue = "0",required = true) long firstId,
            @RequestParam(name = "catId",defaultValue = "0",required = true) long catId){
        // 先更新客户点击的
        categoryService.saveTop(catId);
        // 后更新主页
        categoryService.saveTop(firstId);
    }


}
