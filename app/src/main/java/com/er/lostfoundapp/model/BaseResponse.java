package com.er.lostfoundapp.model;

public class BaseResponse<T> {


    private int code;          // 状态码：200成功，其他失败
    private String msg;        // 提示信息
    private T data;            // 响应数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
