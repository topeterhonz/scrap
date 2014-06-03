package com.hawa.scrap.model;

import java.util.HashMap;
import java.util.Map;

public class Meta {

    private int status;
    private String msg;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
