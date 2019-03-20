package com.zensar.model;


import java.util.ArrayList;
import java.util.List;

public class JohnLewisProduct {

    private String productId;
    private String title;
    private List<ColorSwatch> colorSwatches = new ArrayList<>();

    private Price price;




    public JohnLewisProduct() {
    }

    public JohnLewisProduct(String productId, String title, List<ColorSwatch> colorSwatches, Price price) {
        this.productId = productId;
        this.title = title;
        this.colorSwatches = colorSwatches;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ColorSwatch> getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(List<ColorSwatch> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }


}
