package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 地区名表
 */
@Entity
@Table(name="e_area_name")
public class E_area_name implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "a_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "a_group_hk")
    private String thk;

    @Column(name = "a_group_cn")
    private String tcn;

    @Column(name = "a_group_en")
    private String ten;

    @Column(name = "a_key")
    private String key;

    @Column(name = "a_title_hk")
    private String hk;

    @Column(name = "a_title_cn")
    private String cn;

    @Column(name = "a_title_en")
    private String en;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "a_updatedate")
    private Date updateDate;

    public String getThk() {
        return thk;
    }

    public void setThk(String thk) {
        this.thk = thk;
    }

    public String getTcn() {
        return tcn;
    }

    public void setTcn(String tcn) {
        this.tcn = tcn;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
