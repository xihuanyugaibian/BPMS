package com.bpms.util;

/**
 * json类型响应数据的封装
 */
public class ResultUtil {

    /**
     * 状态码 0 false  ，1  true
     */
    private Integer code;
    /**
     * 状态对应的信息
     */
    private String messager;
    /**
     * 状态对应的数据
     */
    private Object data;

    public static ResultUtil error() {
        return new ResultUtil(0);

    }

    public static ResultUtil error(String messager) {
        return new ResultUtil(0, messager);

    }

    public static ResultUtil ok() {
        return new ResultUtil(1);
    }

    public static ResultUtil ok(String messager) {
        return new ResultUtil(1, messager);
    }

    public static ResultUtil ok(Object data) {
        return new ResultUtil(1, data);
    }

    //三个有参构造方法，状态码；状态码 信息；状态码 数据
    public ResultUtil(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultUtil(Integer code, String messager) {
        this.code = code;
        this.messager = messager;
    }

    public ResultUtil(Integer code) {
        this.code = code;
    }

    public ResultUtil() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessager() {
        return messager;
    }

    public void setMessager(String messager) {
        this.messager = messager;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
