package com.hawa.scrap.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peterho on 18/05/14.
 */

public class Original_Size {

    private Integer width;
    private Integer height;
    private String url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
