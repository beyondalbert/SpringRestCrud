package com.jykj.SpringRestCrud.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by albert on 2015/9/16.
 */
public class Results {
    private int code;
    private String msg;
    private List<Object> results;

    public Results(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.results = new ArrayList<>();
    }

    public Results(int code, String msg, List<Object> results) {
        this(code, msg);
        this.results = results;
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

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }
}
