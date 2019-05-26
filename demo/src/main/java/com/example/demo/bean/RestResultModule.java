package com.example.demo.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回前端类
 */
public class RestResultModule {
    private int code;
    private Map result;
    private String msg;

    public RestResultModule(){
        this.code = 200;
        result = new HashMap<String, Object>();
    }

    public void setMessage(int code, String message){
        this.code = code;
        this.msg = message;
    }

    public void putData(String key, Object value){
        result.put(key, value);
    }

    public void removeData(String key){
        result.remove(key);
    }

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

    public Map getResult() {
        return result;
    }

    public void setResult(Map result) {
        this.result = result;
    }
}
