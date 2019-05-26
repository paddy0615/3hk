package com.example.demo.dao;

import com.example.demo.bean.E_area_name;
import com.example.demo.bean.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "e_area_nameDao")
public interface E_area_nameDao extends JpaRepository<E_area_name,Long> {

    @Query("from E_area_name order by updateDate")
    List<E_area_name> getAllOrderByupdateDate();
}
