package com.example.demo.service;

import com.example.demo.bean.Language;
import com.example.demo.dao.LanguageDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("languageService")
public class LanguageService {
    @Resource
    LanguageDao languageDao;

    @Transactional
    public void save(Language language){
        languageDao.save(language);
    }

    @Modifying
    @Transactional
    public void deleteById(long id){
        languageDao.deleteById(id);
    }

}
