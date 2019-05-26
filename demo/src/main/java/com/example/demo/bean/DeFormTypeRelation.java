package com.example.demo.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * e-form和问题的关系表
 */
@Entity
@Table(name="faqs_eform_relation")
public class DeFormTypeRelation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "er_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;

    @Column(name = "er_et_id")
    private Long etId;   //'e-form类型ID

    @Column(name = "er_dl_id")
    private Long dlId;   //faq详情ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEtId() {
        return etId;
    }

    public void setEtId(Long etId) {
        this.etId = etId;
    }

    public Long getDlId() {
        return dlId;
    }

    public void setDlId(Long dlId) {
        this.dlId = dlId;
    }

}
