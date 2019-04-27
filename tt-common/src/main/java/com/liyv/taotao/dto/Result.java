package com.liyv.taotao.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Result<T> {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
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

    public static Result formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            boolean success = jsonNode.get("success").asBoolean();
            if (success) {
                return new Result<>(success, obj);
            } else {
                return new Result<>(false, jsonNode.get("errorMessage"));
            }
        } catch (Exception e) {
            return null;
        }
    }
}
