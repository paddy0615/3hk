package com.example.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员表
 */
@Entity
@Table(name="faqs_user")
public class User {
    @Id
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_login_id")
    private String loginId;

    @Column(name = "usr_password")
    private String password;

    @Column(name = "usr_role")
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
