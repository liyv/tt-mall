package com.liyv.taotao.entity.dto;

public class Result<T> {
    private boolean success;
    private T data;
    private String errorMessage;

    public Result() {

    }

    //成功时的构造器
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    //错误时的构造器
    public Result(boolean failed, String errorMessage) {
        this.success = failed;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
