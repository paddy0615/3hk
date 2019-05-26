package com.example.demo.dao;

import com.example.demo.bean.DeFormTypeRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "deFormTypeRelationDao")
public interface DeFormTypeRelationDao extends JpaRepository<DeFormTypeRelation,Long> {

    void deleteAllByDlId(long id);

    @Query("from DeFormTypeRelation WHERE dlId = :id")
    List<DeFormTypeRelation> findAllByDlId(@Param("id") long id);
}
