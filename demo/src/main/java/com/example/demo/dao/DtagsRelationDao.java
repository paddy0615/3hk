package com.example.demo.dao;

import com.example.demo.bean.DtagsRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component(value = "dtagsRelationDao")
public interface DtagsRelationDao extends JpaRepository<DtagsRelation,Long> {

    void deleteAllByDlId(long id);
}
