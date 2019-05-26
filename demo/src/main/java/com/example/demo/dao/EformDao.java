package com.example.demo.dao;

import com.example.demo.bean.Eform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "eformDao")
public interface EformDao extends JpaRepository<Eform,Long> {

    List<Eform> findAll();

    @Query(value = "SELECT COUNT(*) FROM e_form WHERE e_random = :s",nativeQuery = true)
    int countByRandom(@Param("s")int s);


    Eform findById(long id);
}
