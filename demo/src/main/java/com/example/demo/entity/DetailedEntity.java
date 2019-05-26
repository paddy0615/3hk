package com.example.demo.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Component(value = "detailedEntity")
public class DetailedEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "dl_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //自增
    private Long id;
    @Column(name = "dl_title")
    private String title;  //详情标题

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
}
