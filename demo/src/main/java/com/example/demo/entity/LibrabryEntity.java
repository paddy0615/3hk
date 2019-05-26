package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Component(value = "librabryEntity")
public class LibrabryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "dl_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long dl_id;
    @Column(name = "dl_title")
    private String dl_title;  //详情标题

    @Column(name = "fl_title")
    private String fl_title;  //fl_title

    @Column(name = "lang_title")
    private String lang_title;  //lang_title

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "dl_updatedate")
    private Date dl_updatedate;

    @Column(name = "dl_status")
    private long dl_status;   //'状态（1发布，0未发布默认）'

    @Column(name = "dl_weights")
    private long dl_weights;   // 权重


    public Long getDl_id() {
        return dl_id;
    }

    public void setDl_id(Long dl_id) {
        this.dl_id = dl_id;
    }

    public String getDl_title() {
        return dl_title;
    }

    public void setDl_title(String dl_title) {
        this.dl_title = dl_title;
    }

    public String getFl_title() {
        return fl_title;
    }

    public void setFl_title(String fl_title) {
        this.fl_title = fl_title;
    }

    public Date getDl_updatedate() {
        return dl_updatedate;
    }

    public void setDl_updatedate(Date dl_updatedate) {
        this.dl_updatedate = dl_updatedate;
    }

    public long getDl_status() {
        return dl_status;
    }

    public void setDl_status(long dl_status) {
        this.dl_status = dl_status;
    }

    public String getLang_title() {
        return lang_title;
    }

    public void setLang_title(String lang_title) {
        this.lang_title = lang_title;
    }

    public long getDl_weights() {
        return dl_weights;
    }

    public void setDl_weights(long dl_weights) {
        this.dl_weights = dl_weights;
    }
}
