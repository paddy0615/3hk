package com.example.demo.dao;

import com.example.demo.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "dfeedbackDao")
public interface DfeedbackDao extends JpaRepository<Feedback,Long> {

    @Query(value = "SELECT f.df_id AS id,\n" +
            "f.df_type AS df_type,\n" +
            "d.dl_id AS dl_id,\n" +
            "d.dl_title AS dl_title,\n" +
            "l.lang_title AS lang_title,\n" +
            "c.cat_title AS cat_title,\n" +
            "f.df_ip AS ip,\n" +
            "f.df_nay_content AS df_content,\n" +
            " f.df_nay_status AS df_nay_status," +
            "f.df_createdate AS df_createdate\n" +
            "FROM faqs_detailed_feedback f,faqs_detailed d,\n" +
            "faqs_language l,faqs_category c\n" +
            "WHERE f.df_dl_id = d.dl_id AND d.dl_lang_id = l.lang_id AND d.dl_cat_id = c.cat_id\n" +
            "AND f.df_id  = :id"
            , nativeQuery = true)
    Feedback getAllById(@Param("id") long id);

    @Query(value = "SELECT f.df_id AS id," +
            " f.df_type AS df_type," +
            " d.dl_id AS dl_id," +
            " d.dl_title AS dl_title," +
            " f.df_ip AS ip," +
            " f.df_nay_content AS df_content," +
            " null AS lang_title," +
            " null AS cat_title," +
            " f.df_nay_status AS df_nay_status," +
            " f.df_createdate AS df_createdate" +
            " FROM faqs_detailed_feedback f,faqs_detailed d" +
            " WHERE f.df_dl_id = d.dl_id"+
            " AND if(:langId > 0,d.dl_lang_id = :langId,1=1)"+
            " AND if(:comment = 1,f.df_nay_content != '' ,1=1)"+
            " AND if(:comment = 2,f.df_nay_content = '' ,1=1)"+
            " AND if(:commentStatu >= 0,f.df_nay_content != '' and f.df_nay_status = :commentStatu,1=1)"+
            " AND if(:type > 0,f.df_type = :type,1=1)"+
            " AND if(:startTime != '',f.df_createdate > :startTime,1=1)"+
            " AND if(:endTime != '',f.df_createdate <= :endTime,1=1)"+
            " ORDER BY f.df_createdate DESC"
            ,countQuery="SELECT COUNT(*)" +
            " FROM faqs_detailed_feedback f,faqs_detailed d" +
            " WHERE f.df_dl_id = d.dl_id"+
            " AND if(:langId > 0,d.dl_lang_id = :langId,1=1)"+
            " AND if(:comment = 1,f.df_nay_content != '' ,1=1)"+
            " AND if(:comment = 2,f.df_nay_content = '' ,1=1)"+
            " AND if(:commentStatu >= 0,f.df_nay_content != '' and f.df_nay_status = :commentStatu,1=1)"+
            " AND if(:type > 0,f.df_type = :type,1=1)"+
            " AND if(:startTime != '',f.df_createdate > :startTime,1=1)"+
            " AND if(:endTime != '',f.df_createdate <= :endTime,1=1)"+
            " ORDER BY f.df_createdate DESC"
            , nativeQuery = true)
    Page<Feedback> getAllByDfType(@Param("langId") long langId,@Param("comment") long comment,@Param("commentStatu") long commentStatu,@Param("type") long type,@Param("startTime") String startTime,@Param("endTime") String endTime,Pageable pageable);


}
