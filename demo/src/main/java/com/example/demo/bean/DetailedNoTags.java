package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * FAQ搜索没有标签表
 */
@Entity
@Table(name="faqs_no_tags")
public class DetailedNoTags implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "nt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "nt_lang_id")
    private Long langId;  // 语言ID

    @Transient
    private String lang_title;  // langTitle

    @Column(name = "nt_ip")
    private String ip;  // ip

    @Column(name = "nt_title")
    private String title;  // 标题

    @Column(name = "nt_count")
    private long count;  // 数量

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "nt_createdate")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getLang_title() {
        return lang_title;
    }

    public void setLang_title(String lang_title) {
        this.lang_title = lang_title;
    }
}
