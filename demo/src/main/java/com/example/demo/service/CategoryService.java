package com.example.demo.service;

import com.example.demo.bean.Category;
import com.example.demo.bean.Detailed;
import com.example.demo.dao.CategoryDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("categoryService")
public class CategoryService {
    @Resource
    CategoryDao categoryDao;
    @Resource
    DetailedService detailedService;

    @Transactional
    public void save(Category category){
        categoryDao.save(category);
    }

    public boolean countCatByCatId(long lang_id){
        boolean falg = true;
        int count = categoryDao.countByLangId(lang_id);
        if(count > 0){
            falg = false;
        }
        return falg;
    }

    @Modifying
    @Transactional
    public void deleteById(long catId){
        // 删除详细
        detailedService.deleteAllByCatId(catId);

        categoryDao.deleteById(catId);
    }

    @Transactional
    public void saveStatus(long catId,long status){
        categoryDao.editStatus(catId,status,new Date());
    }

    @Transactional
    public void saveTop(long catId){
        categoryDao.saveTop(catId,new Date());
    }

}
