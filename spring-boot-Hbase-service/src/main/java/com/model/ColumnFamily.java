package com.model;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

import java.util.HashMap;
import java.util.Map;

public class ColumnFamily {
    private String name;
    private Map<String, Column> columnList;

    public ColumnFamily(){
        columnList = new HashMap<>();
    }

    public Map<String, Column> getColumnMap() {
        return columnList;
    }

    public void setColumnMap(Map<String, Column> columnList) {
        this.columnList = columnList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
