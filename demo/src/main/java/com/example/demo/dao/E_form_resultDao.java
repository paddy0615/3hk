package com.example.demo.dao;

import com.example.demo.bean.E_form_result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "e_form_resultDao")
public interface E_form_resultDao extends JpaRepository<E_form_result,Long> {

}
