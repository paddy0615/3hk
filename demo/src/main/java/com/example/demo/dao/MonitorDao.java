package com.example.demo.dao;

import com.example.demo.bean.Detailed;
import com.example.demo.bean.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "monitorDao")
public interface MonitorDao extends JpaRepository<Monitor,Long> {

    @Query(value = "SELECT * FROM faqs_monitor" +
            " WHERE m_clientip = :ip" +
            " AND m_dl_id > 0" +
            " ORDER BY m_createdate DESC" +
            " LIMIT 0,1",nativeQuery = true)
    Monitor getAllByClientip(@Param("ip")String ip);



}
