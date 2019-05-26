package com.example.demo.dao;

import com.example.demo.bean.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "languageDao")
public interface LanguageDao extends JpaRepository<Language,Long> {

    Language findById(long langId);

    List<Language> findAllByTitleContaining(String s);

    @Query("select title from Language where id = :langId")
    String getByTitle(@Param("langId")  long langId);
}
