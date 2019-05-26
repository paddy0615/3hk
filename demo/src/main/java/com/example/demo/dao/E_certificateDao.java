package com.example.demo.dao;

import com.example.demo.bean.E_certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component(value = "e_certificateDao")
public interface E_certificateDao extends JpaRepository<E_certificate,Long> {

    E_certificate findById(long id);

}
