package com.example.demo.dao;

import com.example.demo.bean.E_form_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "e_form_typeDao")
public interface E_form_typeDao extends JpaRepository<E_form_type,Long> {

    E_form_type findById(long id);


    @Query(value = "\n" +
            "SELECT et_id,et_title_hk,et_title_cn,et_title_en FROM e_form_type ,faqs_eform_relation\n" +
            "WHERE et_id = er_et_id\n" +
            "AND er_dl_id = :dlId\n" +
            "ORDER BY er_id",nativeQuery = true)
    List<E_form_type> getAllByDlId(@Param("dlId") long dlId);

    /**
     * 开放1,3,6
     * @return
     */
    @Query(value = "SELECT et_id,et_title_hk,et_title_cn,et_title_en FROM e_form_type" +
            " where et_id IN (1,3,6)"+
            " ORDER BY et_id",nativeQuery = true)
    List<E_form_type> getAllByDlIdtest();

}
