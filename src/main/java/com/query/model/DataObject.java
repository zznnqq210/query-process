package com.query.model;

import java.time.LocalDateTime;

// 测试对象
public class DataObject {

    private Long id;

    private LocalDateTime createTime;

    private String name;

    private int type;

    private boolean flag;

    public DataObject(Long id, LocalDateTime createTime, String name, int type, boolean flag) {
        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.type = type;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", flag=" + flag +
                '}';
    }
}
