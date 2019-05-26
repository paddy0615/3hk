package com.example.demo.dao;

import com.example.demo.entity.DetailedEntity;
import com.example.demo.entity.LibrabryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component(value = "librabryEntityDao")
public interface LibrabryEntityDao extends JpaRepository<LibrabryEntity,Long> {

    @Query(value = "SELECT " +
            " d.dl_id," +
            " d.dl_title," +
            " d.dl_lang_id," +
            " d.dl_updatedate," +
            " d.dl_status," +
            " fl.fl_title," +
            " l.lang_title," +
            " d.dl_weights" +
            " FROM faqs_detailed d,faqs_librabry fl,faqs_language l" +
            " WHERE d.dl_fl_id = fl.fl_id AND d.dl_lang_id = l.lang_id" +
            " AND if(:fl_id > 0,fl.fl_id = :fl_id,1=1)"+
            " AND if(:langId > 0,d.dl_lang_id = :langId,1=1)"+
            " AND if(:dl_status >= 0,d.dl_status = :dl_status,1=1)"+
            " ORDER BY fl.fl_id,l.lang_id"
            ,countQuery="SELECT count(*)" +
            " FROM faqs_detailed d,faqs_librabry fl,faqs_language l" +
            " WHERE d.dl_fl_id = fl.fl_id AND d.dl_lang_id = l.lang_id" +
            " AND if(:fl_id > 0,fl.fl_id = :fl_id,1=1)"+
            " AND if(:langId > 0,d.dl_lang_id = :langId,1=1)"+
            " AND if(:dl_status >= 0,d.dl_status = :dl_status,1=1)"+
            " ORDER BY fl.fl_id,l.lang_id"
            ,nativeQuery = true)
    Page<LibrabryEntity> getLibrabryEntity(@Param("fl_id") long fl_id, @Param("langId") long langId, @Param("dl_status") long dl_status, Pageable pageable);

    /**
     * 2.2后台搜索
     * @param status
     * @param searchs
     * @return
     */
    @Query(value = "SELECT \n" +
            " d.dl_id,\n" +
            " d.dl_title,\n" +
            " d.dl_lang_id,\n" +
            " d.dl_updatedate,\n" +
            " d.dl_status,\n" +
            " fl.fl_title,\n" +
            " l.lang_title,\n" +
            " d.dl_weights\n" +
            "FROM  faqs_detailed d\n" +
            "INNER JOIN faqs_dtags_relation dr ON (dr.dr_dl_id = d.dl_id)\n" +
            "INNER JOIN faqs_detailed_tags dt ON (dr.dr_dt_id = dt.dt_id)\n" +
            "LEFT JOIN faqs_dl_hotspot ON (d.dl_id = dlh_dl_id)\n" +
            "LEFT JOIN faqs_librabry fl ON (d.dl_fl_id = fl.fl_id)\n" +
            "LEFT JOIN faqs_language l ON (d.dl_lang_id = l.lang_id)\n" +
            "WHERE 1=1\n" +
            "AND if(:status > 0,d.dl_status = :status,1=1)\n"+
            "AND dt.dt_title IN(:searchs)\n" +
            "GROUP BY d.dl_id\n" +
            "ORDER BY COUNT(d.dl_id) DESC,d.dl_weights DESC,dlh_search_count DESC,d.dl_updatedate DESC", nativeQuery = true)
    List<LibrabryEntity> getSearchTagsNew(@Param("status") long status, @Param("searchs")List<String> searchs);



}
