package com.liyv.taotao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * 商品种类实体
 */
public class TaoItemCatEntity {

    private long id;
    private long parentId;
    @JsonProperty("text")
    private String name;
    private int status;
    private int sortOrder;
    private char isParent;
    private Date created;
    private Date updated;
    private List<TaoItemCatEntity> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public char getIsParent() {
        return isParent;
    }

    public void setIsParent(char isParent) {
        this.isParent = isParent;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<TaoItemCatEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TaoItemCatEntity> children) {
        this.children = children;
    }
}
