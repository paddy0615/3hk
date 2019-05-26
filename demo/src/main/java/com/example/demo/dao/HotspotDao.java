package com.example.demo.dao;

import com.example.demo.bean.Hotspot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "hotspotDao")
public interface HotspotDao extends JpaRepository<Hotspot,Long> {

    Hotspot findByDlId(long id);

    void deleteByDlId(long dlid);

}
