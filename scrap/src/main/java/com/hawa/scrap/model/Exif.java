package com.hawa.scrap.model;


import java.util.HashMap;
import java.util.Map;

public class Exif {

    private String camera;
    private Integer iSO;
    private String focalLength;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public Integer getISO() {
        return iSO;
    }

    public void setISO(Integer iSO) {
        this.iSO = iSO;
    }

    public String getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(String focalLength) {
        this.focalLength = focalLength;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
