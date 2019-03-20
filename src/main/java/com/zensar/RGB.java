package com.zensar;

public enum  RGB {

    Red("FF0000"), Blue("0000FF"), Pink("FFC0CB"), Black("000000"),
    Green("008000"), Grey("808080"),
    Multi(""), Orange("FFA500"), Purple("800080"), White("FFFFFF"), Yellow("FFFF00");

    private String hex;

    RGB(String hex) {
        this.hex = hex;
    }


    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}