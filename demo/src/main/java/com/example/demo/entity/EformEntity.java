package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * e-form 记录表
 * 请查看sql , 对应的字段
 */
@Entity
@Component(value = "eformEntity")
public class EformEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "e_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "e_lang_id")
    private Long langId;    // 语言ID

    @Column(name = "e_type")
    private String type;

    @Column(name = "e_pnr")
    private String pnr;

    @Column(name = "e_first_name")
    private String firstname;

    @Column(name = "e_last_name")
    private String lastname;

    @Column(name = "e_email")
    private String email;

    @Column(name = "e_trip_type",columnDefinition="long default 0")
    private Long triptype = (long)0;

    @Column(name = "e_flie")
    private String flie;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "e_updatedate")
    private Date updateDate;

    @Column(name = "e_random",columnDefinition="int default 0")
    private int random = 0;

    @Column(name = "langa_title")
    private String langaTitle;  // langaTitle

    @Column(name = "type_title")
    private String typeTitle;  // typeTitle


    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }

    public String getLangaTitle() {
        return langaTitle;
    }

    public void setLangaTitle(String langaTitle) {
        this.langaTitle = langaTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTriptype() {
        return triptype;
    }

    public void setTriptype(Long triptype) {
        this.triptype = triptype;
    }

    public String getFlie() {
        return flie;
    }

    public void setFlie(String flie) {
        this.flie = flie;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }
}
