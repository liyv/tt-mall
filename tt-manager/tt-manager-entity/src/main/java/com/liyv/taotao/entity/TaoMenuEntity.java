package com.liyv.taotao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TaoMenuEntity {
    private int id;
    @JsonProperty("text")
    private String name;
    private int pid;
    private String url;
    private List<TaoMenuEntity> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TaoMenuEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TaoMenuEntity> children) {
        this.children = children;
    }
}
