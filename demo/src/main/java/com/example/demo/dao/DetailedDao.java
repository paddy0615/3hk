package com.example.demo.dao;

import com.example.demo.bean.Detailed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component(value = "detailedDao")
@Transactional(readOnly = true)
public interface DetailedDao extends JpaRepository<Detailed,Long> {
    Detailed findById(long id);

    Detailed getByFlIdAndLangId(long flId,long longId);

    List<Detailed> findAllByLangIdAndCatId(long longId,long catId);

    List<Detailed> findAllByLangIdAndCatIdAndStatus(long longId,long catId,long status);

    @Query("select new Detailed(d.id,d.title) from Detailed d where d.langId = :langId  and (d.title like %:search%  or d.contentTxt like %:search%) and d.status = 1")
    List<Detailed> getSearch(@Param("langId")long langId, @Param("search")String search);

    List<Detailed> findAllByTitleContaining(String s);
    List<Detailed> findAllByStatusAndTitleContaining(long status,String s);

    List<Detailed> findAllByLangIdAndCatIdAndTitleContainingOrContentTxt(long langId,long catId,String s,String sx);

    @Query("select d from Detailed d where d.langId = :langId and d.catId = :catId and (d.title like %:search%  or d.contentTxt like %:search%)")
    List<Detailed> getSerDateAll(@Param("langId")long langId,@Param("catId")long catId,@Param("search")String search);

    @Query(value = "SELECT d.* FROM  faqs_detailed d\n" +
            "            INNER JOIN faqs_dtags_relation dr ON (dr.dr_dl_id = d.dl_id)\n" +
            "            INNER JOIN faqs_detailed_tags dt ON (dr.dr_dt_id = dt.dt_id)\n" +
            "            WHERE d.dl_lang_id = :langId and d.dl_cat_id = :catId  and dt.dt_title IN(:searchs)\n" +
            "            GROUP BY d.dl_id\n" +
            "            ORDER BY COUNT(d.dl_id) DESC",nativeQuery = true)
    List<Detailed> getAdminSetTags(@Param("langId")long langId,@Param("catId")long catId,@Param("searchs")List<String> searchs);




    int countByCatId(long id);

    // 按搜索点击数量获取
    @Query("select new Detailed(d.id,d.title) from Detailed d,Hotspot h where d.id = h.dlId and d.status = 1  order by h.searchCount desc")
    List<Detailed> getHpSearchCount();
    // 按搜索点击数量获取
    @Query("select new Detailed(d.id,d.title) from Detailed d,Hotspot h where d.id = h.dlId and d.status = 1 and d.langId = :langId  order by h.searchCount desc")
    Page<Detailed> getHpSearchCount1(@Param("langId")long langId,Pageable pageable);

    List<Detailed> findAllByCatId(long catId);


    @Modifying
    @Query("update Detailed d set d.status = :status , d.updateDate = :date where d.id = :dlId")
    void editStatus(@Param("dlId")long dlId, @Param("status")long status , @Param("date")Date date);

    @Modifying
    @Query("update Detailed d set d.orderTopDate = :date where d.id = :dlId")
    void saveTop(@Param("dlId")long dlId,@Param("date")Date date);


    @Query(value = "select new Detailed(l.title,d.id,d.title,d.contentTxt) from Detailed d,Librabry l" +
            " where d.flId = l.id and  d.status = 1 and d.langId = :langId")
    List<Detailed> getAllByLangId(@Param("langId")long langId);
}
