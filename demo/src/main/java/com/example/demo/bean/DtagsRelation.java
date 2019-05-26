package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * FAQ详情和标签关系表
 */
@Entity
@Table(name="faqs_dtags_relation")
public class DtagsRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "dr_dt_id")
    private Long dtId;   //详情标签ID

    @Column(name = "dr_dl_id")
    private Long dlId;   //faq详情ID

    @Column(name = "dr_order")
    private Long ord;   //排序

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "dr_createdate")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDtId() {
        return dtId;
    }

    public void setDtId(Long dtId) {
        this.dtId = dtId;
    }

    public Long getDlId() {
        return dlId;
    }

    public void setDlId(Long dlId) {
        this.dlId = dlId;
    }

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
