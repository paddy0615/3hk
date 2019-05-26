package com.example.demo.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * FAQ热点表
 */
@Entity
@Table(name="faqs_dl_hotspot")
public class Hotspot implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dlh_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;


    @Column(name = "dlh_dl_id")
    private Long dlId;

    @Column(name = "dlh_search_count")
    private long searchCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDlId() {
        return dlId;
    }

    public void setDlId(Long dlId) {
        this.dlId = dlId;
    }

    public long getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(long searchCount) {
        this.searchCount = searchCount;
    }
}
