package com.bawei.wuruitao.model.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ProjectName: WuRuitao20200106
 * PackageName: com.bawei.wuruitao.model.entity
 * ClassName:   CategoryBean
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/6_9:36
 */
@Entity
public class CategoryBean {
    @Id(autoincrement = true)
    private Long id;

    private String json;

    @Generated(hash = 1706594872)
    public CategoryBean(Long id, String json) {
        this.id = id;
        this.json = json;
    }

    @Generated(hash = 1870435730)
    public CategoryBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}

