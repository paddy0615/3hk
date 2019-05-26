package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 反馈信息表
 */
@Entity
@Table(name="faqs_detailed_feedback")
public class DetailedFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "df_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "df_type")
    private Long type;  // 类别(1支持,2反对)

    @Column(name = "df_dl_id")
    private Long dlId;

    @Column(name = "df_ip")
    private String ip;  // ip

    @Column(name = "df_nay_content")
    private String content;  // 反对内容

    @Column(name = "df_nay_email")
    private String email;  // 反对email

    @Column(name = "df_nay_number")
    private String number;  // 反对number

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "df_createdate")
    private Date createDate;

    @Column(name = "df_nay_status",columnDefinition="long default 0")
    private Long contentStatus = (long)0;  // 反馈信息状态（0关闭,1打开）


    @Transient
    private String lang_title;  // langTitle
    @Transient
    private String detailed_title;  // detailed_title


    public Long getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(Long contentStatus) {
        this.contentStatus = contentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLang_title() {
        return lang_title;
    }

    public void setLang_title(String lang_title) {
        this.lang_title = lang_title;
    }

    public String getDetailed_title() {
        return detailed_title;
    }

    public void setDetailed_title(String detailed_title) {
        this.detailed_title = detailed_title;
    }
}
