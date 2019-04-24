package com.liyv.taotao.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TaoItemCatDTO {

    private long id;
    private String text;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
