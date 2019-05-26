package com.example.demo.dao;

import com.example.demo.bean.Eform;
import com.example.demo.entity.EformEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "eformEntityDao")
public interface EformEntityDao extends JpaRepository<EformEntity,Long> {

    @Query(value = "SELECT e.e_id," +
            " e.e_lang_id," +
            " e.e_type," +
            " e.e_pnr," +
            " e.e_first_name," +
            " e.e_last_name," +
            " e.e_trip_type," +
            " e.e_email," +
            " e.e_updatedate," +
            " e.e_flie," +
            " e.e_random," +
            " l.lang_title as langa_title," +
            " t.et_title_en as type_title" +
            " FROM e_form e,faqs_language l,e_form_type t"+
            " WHERE e.e_lang_id = l.lang_id and e.e_type = t.et_id"+
            " AND if(:langId > 0,e.e_lang_id = :langId,1=1)"+
            " AND if(:type > 0,e.e_type = :type,1=1)"+
            " AND if(:startTime != '',e.e_updatedate > :startTime,1=1)"+
            " AND if(:endTime != '',e.e_updatedate <= :endTime,1=1)"+
            " AND if(:searchTest != '',( e.e_pnr like %:searchTest% or e.e_first_name like %:searchTest% or e.e_last_name like %:searchTest% or e.e_random like %:searchTest% ),1=1)"+
            " ORDER BY e.e_updatedate DESC"
            ,countQuery="SELECT COUNT(*)" +
            " FROM e_form e,faqs_language l,e_form_type t"+
            " WHERE e.e_lang_id = l.lang_id and e.e_type = t.et_id"+
            " AND if(:langId > 0,e.e_lang_id = :langId,1=1)"+
            " AND if(:type > 0,e.e_type = :type,1=1)"+
            " AND if(:startTime != '',e.e_updatedate > :startTime,1=1)"+
            " AND if(:endTime != '',e.e_updatedate <= :endTime,1=1)"+
            " AND if(:searchTest != '',( e.e_pnr like %:searchTest% or e.e_first_name like %:searchTest% or e.e_last_name like %:searchTest% or e.e_random like %:searchTest% ),1=1)"+
            " ORDER BY e.e_updatedate DESC"
            , nativeQuery = true)
    Page<EformEntity> getEformEntityPage(@Param("langId") long langId, @Param("type") long type, @Param("startTime") String startTime, @Param("endTime") String endTime,@Param("searchTest") String searchTest, Pageable pageable);


}
