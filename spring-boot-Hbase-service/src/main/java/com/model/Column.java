package com.model;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

public class Column {
    private String name;
    private String dataType;
    private byte[] value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }
}
