package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 对接口返回记录
 */
@Entity
@Table(name="e_form_result")
public class E_form_result implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "er_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "er_e_id")
    private Long eid;

    @Column(name = "er_result")
    private String result;

    @Column(name = "er_result_xml")
    private String resultxml;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultxml() {
        return resultxml;
    }

    public void setResultxml(String resultxml) {
        this.resultxml = resultxml;
    }
}
