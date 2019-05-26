package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 反馈信息表
 */
@Entity
@Component(value = "feedback")
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;
    @Column(name = "df_type")
    private Long dfType;  // 类别(1支持,2反对)
    @Column(name = "dl_id")
    private Long dlId;
    @Column(name = "dl_title")
    private String dlTitle; // 详情标题
    @Column(name = "ip")
    private String ip;  // ip
    @Column(name = "df_content")
    private String content;  // 反对内容

    @Column(name = "lang_title")
    private String langTitle;  // 语言标题
    @Column(name = "cat_title")
    private String catTitle;  // 内容标题

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "df_createdate")
    private Date createDate;

    @Column(name = "df_nay_status")
    private Long contentStatus;  // 反馈信息状态（0关闭,1打开）


    public Long getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(Long contentStatus) {
        this.contentStatus = contentStatus;
    }

    public String getDlTitle() {
        return dlTitle;
    }

    public void setDlTitle(String dlTitle) {
        this.dlTitle = dlTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDfType() {
        return dfType;
    }

    public void setDfType(Long dfType) {
        this.dfType = dfType;
    }

    public Long getDlId() {
        return dlId;
    }

    public void setDlId(Long dlId) {
        this.dlId = dlId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLangTitle() {
        return langTitle;
    }

    public void setLangTitle(String langTitle) {
        this.langTitle = langTitle;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public void setCatTitle(String catTitle) {
        this.catTitle = catTitle;
    }
}
