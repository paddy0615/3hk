package com.example.demo.dao;

import com.example.demo.bean.DetailedTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "detailedTagsDao")
public interface DetailedTagsDao extends JpaRepository<DetailedTags,Long> {

    @Query("SELECT d FROM DetailedTags d where d.title = :title")
    List<DetailedTags> findByTitle(@Param("title") String title);

    @Query("SELECT dt.title FROM DetailedTags dt,DtagsRelation dr where dt.id = dr.dtId and dr.dlId = :dlId order by dr.ord")
    String [] getAllByDlId(@Param("dlId") long dlId);


}
