package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * e-form 记录表
 * 请查看sql , 对应的字段
 */
@Entity
@Table(name="e_form")
public class Eform implements Serializable {
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

    @Column(name = "e_trip_departing")
    private String departing;

    @Column(name = "e_trip_going")
    private String going;

    @Column(name = "e_trip_departing_new")
    private String departingnew;

    @Column(name = "e_trip_going_new")
    private String goingnew;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "e_trip_departingdate")
    private Date departingdate;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "e_trip_goingdate")
    private Date goingdate;

    @Column(name = "e_relation")
    private Long relation;

    @Column(name = "e_relation_eid",columnDefinition="long default 0")
    private Long relationeid = (long)0;

    @Column(name = "e_certificate_type",columnDefinition="long default 0")
    private Long ecertificatetype = (long)0;

    @Column(name = "e_first_name_new")
    private String firstnamenew;

    @Column(name = "e_last_name_new")
    private String lastnamenew;

    @Column(name = "e_flie")
    private String flie;

    @Column(name = "e_payment_type",columnDefinition="long default 0")
    private Long paytype = (long)0;

    @Column(name = "e_payment_transaction")
    private String paytransaction;

    @Column(name = "e_payment_amount")
    private String payamount;

    @Column(name = "e_currency_id",columnDefinition="long default 0")
    private Long paycurrencyid = (long)0;

    @Column(name = "e_payment_accounts")
    private String payaccounts;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "e_updatedate")
    private Date updateDate;

    @Column(name = "e_random",columnDefinition="int default 0")
    private int random = 0;

    @Column(name = "e_dl_id",columnDefinition="long default 0")
    private Long dlId = (long)0;


    public Long getDlId() {
        return dlId;
    }

    public void setDlId(Long dlId) {
        this.dlId = dlId;
    }

    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }

    public Long getPaycurrencyid() {
        return paycurrencyid;
    }

    public void setPaycurrencyid(Long paycurrencyid) {
        this.paycurrencyid = paycurrencyid;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDeparting() {
        return departing;
    }

    public void setDeparting(String departing) {
        this.departing = departing;
    }

    public String getGoing() {
        return going;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public String getDepartingnew() {
        return departingnew;
    }

    public void setDepartingnew(String departingnew) {
        this.departingnew = departingnew;
    }

    public String getGoingnew() {
        return goingnew;
    }

    public void setGoingnew(String goingnew) {
        this.goingnew = goingnew;
    }

    public Date getDepartingdate() {
        return departingdate;
    }

    public void setDepartingdate(Date departingdate) {
        this.departingdate = departingdate;
    }

    public Date getGoingdate() {
        return goingdate;
    }

    public void setGoingdate(Date goingdate) {
        this.goingdate = goingdate;
    }

    public Long getRelation() {
        return relation;
    }

    public void setRelation(Long relation) {
        this.relation = relation;
    }

    public Long getRelationeid() {
        return relationeid;
    }

    public void setRelationeid(Long relationeid) {
        this.relationeid = relationeid;
    }

    public Long getEcertificatetype() {
        return ecertificatetype;
    }

    public void setEcertificatetype(Long ecertificatetype) {
        this.ecertificatetype = ecertificatetype;
    }

    public String getFirstnamenew() {
        return firstnamenew;
    }

    public void setFirstnamenew(String firstnamenew) {
        this.firstnamenew = firstnamenew;
    }

    public String getLastnamenew() {
        return lastnamenew;
    }

    public void setLastnamenew(String lastnamenew) {
        this.lastnamenew = lastnamenew;
    }

    public String getFlie() {
        return flie;
    }

    public void setFlie(String flie) {
        this.flie = flie;
    }

    public Long getPaytype() {
        return paytype;
    }

    public void setPaytype(Long paytype) {
        this.paytype = paytype;
    }

    public String getPaytransaction() {
        return paytransaction;
    }

    public void setPaytransaction(String paytransaction) {
        this.paytransaction = paytransaction;
    }

    public String getPayamount() {
        return payamount;
    }

    public void setPayamount(String payamount) {
        this.payamount = payamount;
    }


    public String getPayaccounts() {
        return payaccounts;
    }

    public void setPayaccounts(String payaccounts) {
        this.payaccounts = payaccounts;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
