package com.example.demo.dao;

import com.example.demo.bean.DetailedNoTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "detailedNoTagsDao")
public interface DetailedNoTagsDao extends JpaRepository<DetailedNoTags,Long> {

    DetailedNoTags findByTitle(String s);


    @Query(value = "SELECT *" +
           /* " f.nt_id AS nt_id," +
            " f.nt_lang_id AS nt_lang_id," +
            " f.nt_ip as nt_ip," +
            " f.nt_title AS nt_title," +
            " f.nt_count AS nt_count," +
            " f.nt_createdate AS nt_createdate," +
            " l.lang_title AS lang_title" +*/
            " FROM faqs_no_tags f" +
            " WHERE 1=1"+
            " AND if(:langId > 0,f.nt_lang_id = :langId,1=1)"+
            " AND if(:startTime != '',f.nt_createdate > :startTime,1=1)"+
            " AND if(:endTime != '',f.nt_createdate <= :endTime,1=1)"+
            " ORDER BY f.nt_createdate DESC"
            , nativeQuery = true)
    List<DetailedNoTags> getAllByDNTs(@Param("langId") long langId, @Param("startTime") String startTime, @Param("endTime") String endTime);


}
