package com.example.demo.dao;

import com.example.demo.entity.DetailedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "detailedEntityDao")
public interface DetailedEntityDao extends JpaRepository<DetailedEntity,Long> {


    /**
     * 2.2前台搜索(注意langId <0,开放跨语言搜索)
     * @param status
     * @param searchs
     * @return
     */
    @Query(value = "SELECT d.dl_id,d.dl_title\n" +
            "FROM  faqs_detailed d\n" +
            "INNER JOIN faqs_dtags_relation dr ON (dr.dr_dl_id = d.dl_id)\n" +
            "INNER JOIN faqs_detailed_tags dt ON (dr.dr_dt_id = dt.dt_id)\n" +
            "LEFT JOIN faqs_dl_hotspot ON (d.dl_id = dlh_dl_id)\n" +
            "WHERE 1=1\n" +
            "AND if(:langId < 0,d.dl_lang_id = :langId,1=1)\n"+
            "AND if(:status > 0,d.dl_status = :status,1=1)\n"+
            "AND dt.dt_title IN(:searchs)\n" +
            "GROUP BY d.dl_id\n" +
            "ORDER BY COUNT(d.dl_id) DESC,d.dl_weights DESC,dlh_search_count DESC,d.dl_updatedate DESC", nativeQuery = true)
    List<DetailedEntity> getSearchTags(@Param("langId") long langId,@Param("status") long status,@Param("searchs")List<String> searchs);

    @Query(value = "SELECT count(*) FROM (\n" +
            "SELECT d.dl_id,d.dl_title\n" +
            "FROM  faqs_detailed d\n" +
            "INNER JOIN faqs_dtags_relation dr ON (dr.dr_dl_id = d.dl_id)\n" +
            "INNER JOIN faqs_detailed_tags dt ON (dr.dr_dt_id = dt.dt_id)\n" +
            "LEFT JOIN faqs_dl_hotspot ON (d.dl_id = dlh_dl_id)\n" +
            "WHERE 1=1\n" +
            "AND d.dl_status = 1 \n" +
            "AND dt.dt_title IN(:searchs)\n" +
            "GROUP BY d.dl_id\n" +
            "ORDER BY COUNT(d.dl_id) DESC,d.dl_weights DESC,dlh_search_count DESC,d.dl_updatedate DESC" +
            ") a", nativeQuery = true)
    long getNoTagsCounts(@Param("searchs")List<String> searchs);

    @Query(value = "SELECT count(*) FROM (\n" +
            "SELECT d.dl_id,d.dl_title\n" +
            "FROM  faqs_detailed d\n" +
            "INNER JOIN faqs_dtags_relation dr ON (dr.dr_dl_id = d.dl_id)\n" +
            "INNER JOIN faqs_detailed_tags dt ON (dr.dr_dt_id = dt.dt_id)\n" +
            "LEFT JOIN faqs_dl_hotspot ON (d.dl_id = dlh_dl_id)\n" +
            "WHERE 1=1\n" +
            "AND d.dl_status = 1 \n" +
            "AND dt.dt_title = (:s)\n" +
            "GROUP BY d.dl_id\n" +
            "ORDER BY COUNT(d.dl_id) DESC,d.dl_weights DESC,dlh_search_count DESC,d.dl_updatedate DESC\n" +
            ") a", nativeQuery = true)
    long getNoTagsCount(@Param("s")String s);

    @Query(value = "SELECT d.dl_id,d.dl_title FROM faqs_detailed d" +
            " INNER JOIN (" +
            " SELECT m_dl_id,m_dl_id_father,COUNT(1) FROM faqs_monitor" +
            " WHERE m_dl_id_father > 0" +
            " AND m_dl_id_father = :dlId" +
            " AND m_dl_id != :dlId" +
            " GROUP BY m_dl_id,m_dl_id_father" +
            " ORDER BY COUNT(1) DESC,m_createdate DESC " +
            " LIMIT 0,5" +
            " ) a" +
            " ON a.m_dl_id = d.dl_id",nativeQuery = true)
    List<DetailedEntity> getSmartGuide(@Param("dlId")long dlId);
}
