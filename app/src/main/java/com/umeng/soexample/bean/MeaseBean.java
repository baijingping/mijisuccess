package com.umeng.soexample.bean;

/**
 * Created by Shinelon on 2018/10/25.
 */

public class MeaseBean<T> {
  private String msg;
  private String code;
  private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
