package com.example.demo.dao;

import com.example.demo.bean.Librabry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component(value = "librabryDao")
public interface LibrabryDao extends JpaRepository<Librabry,Long> {

    @Query("select title from Librabry where id = :id")
    String getByTitle(@Param("id") long id);
}
