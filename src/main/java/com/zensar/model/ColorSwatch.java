package com.zensar.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zensar.deserializer.BasicColorDeserializer;


public class ColorSwatch {

    private String color;
    @JsonDeserialize(using = BasicColorDeserializer.class)
    @JsonSetter("basicColor")
    private String rgbColor;
    private String skuId;

    public ColorSwatch() {
    }


    public ColorSwatch(String color, String rgbColor, String skuId) {
        this.color = color;
        this.rgbColor = rgbColor;
        this.skuId = skuId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRgbColor() {
        return rgbColor;
    }

    public void setBasicColor(String rgbColor) {
        this.rgbColor = rgbColor;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
