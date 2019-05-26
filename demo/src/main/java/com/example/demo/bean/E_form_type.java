package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * eform 分类
 */
@Entity
@Table(name="e_form_type")
public class E_form_type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "et_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "et_title_hk")
    private String hk;

    @Column(name = "et_title_cn")
    private String cn;

    @Column(name = "et_title_en")
    private String en;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHk() {
        return hk;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
