package com.hawa.scrap.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Photo {

    public Photo() {

    }
    private String caption;
    private List<Alt_Size> alt_sizes = new ArrayList<Alt_Size>();
    private Original_Size original_size;
    private Exif exif;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<Alt_Size> getAlt_sizes() {
        return alt_sizes;
    }

    public void setAlt_sizes(List<Alt_Size> alt_sizes) {
        this.alt_sizes = alt_sizes;
    }

    public Original_Size getOriginal_size() {
        return original_size;
    }

    public void setOriginal_size(Original_Size original_size) {
        this.original_size = original_size;
    }

    public Exif getExif() {
        return exif;
    }

    public void setExif(Exif exif) {
        this.exif = exif;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
