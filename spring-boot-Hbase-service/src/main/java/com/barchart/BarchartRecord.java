package com.barchart;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

public class BarchartRecord {
    private String name;
    private int yVal;

    public int getyVal() {
        return yVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
