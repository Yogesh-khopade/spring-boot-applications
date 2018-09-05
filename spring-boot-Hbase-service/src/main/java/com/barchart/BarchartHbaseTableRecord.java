package com.barchart;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.common.CommonConstants;
import com.model.Column;
import com.model.ColumnFamily;

public class BarchartHbaseTableRecord {
    private String tableName;
    private Map<String, ColumnFamily> columnFamilies;

    public BarchartHbaseTableRecord(){
        this.tableName = BarChartHbaseTableUtils.BARCHART_TABLENAME;
        columnFamilies = new HashMap<>();
        configureColumnFamilies();
    }

    private void configureColumnFamilies(){
        ColumnFamily singleColumnFamily = new ColumnFamily();
        singleColumnFamily.setName(BarChartHbaseTableUtils.SINGLE_CF_NAME);
        configureColsForCf(singleColumnFamily);
        getColumnFamilies().put(singleColumnFamily.getName(), singleColumnFamily);
    }

    private void configureColsForCf(ColumnFamily columnFamily){
        Column nameCol = new Column();
        nameCol.setName(BarChartHbaseTableUtils.NAME_COL_NAME);
        nameCol.setDataType(CommonConstants.STRING_DATA_TYPE);

        Column yValCol = new Column();
        yValCol.setName(BarChartHbaseTableUtils.YVAL_COL_NAME);
        yValCol.setDataType(CommonConstants.INT_DATA_TYPE);

        columnFamily.getColumnMap().put(nameCol.getName(), nameCol);
        columnFamily.getColumnMap().put(yValCol.getName(), yValCol);
    }

    public static BarchartHbaseTableRecord getBarChartHbaseTableRecord(BarchartRecord barchartRecord){
        BarchartHbaseTableRecord barchartHbaseTableRecord = new BarchartHbaseTableRecord();
        Column nameCol = barchartHbaseTableRecord.getColumnFamilies().get(BarChartHbaseTableUtils.SINGLE_CF_NAME).getColumnMap().get(BarChartHbaseTableUtils.NAME_COL_NAME);
        nameCol.setValue(Bytes.toBytes(barchartRecord.getName()));

        Column yValCol = barchartHbaseTableRecord.getColumnFamilies().get(BarChartHbaseTableUtils.SINGLE_CF_NAME).getColumnMap().get(BarChartHbaseTableUtils.YVAL_COL_NAME);
        yValCol.setValue(Bytes.toBytes(barchartRecord.getyVal()));

        return barchartHbaseTableRecord;
    }

    public static BarchartRecord getBarChartRecord(BarchartHbaseTableRecord barchartHbaseTableRecord){
        BarchartRecord barchartRecord = new BarchartRecord();

        Column nameCol = barchartHbaseTableRecord.getColumnFamilies().get(BarChartHbaseTableUtils.SINGLE_CF_NAME).getColumnMap().get(BarChartHbaseTableUtils.NAME_COL_NAME);
        barchartRecord.setName(Bytes.toString(nameCol.getValue()));

        Column yValCol = barchartHbaseTableRecord.getColumnFamilies().get(BarChartHbaseTableUtils.SINGLE_CF_NAME).getColumnMap().get(BarChartHbaseTableUtils.YVAL_COL_NAME);
        barchartRecord.setyVal(Bytes.toInt(yValCol.getValue()));

        return barchartRecord;
    }

    public void insertRecord(Admin admin, byte[] rowKey, BarchartHbaseTableRecord barchartHbaseTableRecord, Table table) throws IOException {
        Map<String, ColumnFamily> columnFamilies = barchartHbaseTableRecord.getColumnFamilies();
        for(Map.Entry<String, ColumnFamily> entry : columnFamilies.entrySet()){
            ColumnFamily columnFamily = entry.getValue();
            Map<String, Column> columns = columnFamily.getColumnMap();
            for(Map.Entry<String, Column> entry1 : columns.entrySet()){
                Column column = entry1.getValue();

                Put p = new Put(rowKey);
                p.addImmutable(columnFamily.getName().getBytes(), Bytes.toBytes(column.getName()), column.getValue());
                table.put(p);
            }
        }
    }

    public byte[] getRowKeyForRecord(BarchartHbaseTableRecord barchartHbaseTableRecord, int incrementalId){
        return Bytes.toBytes(incrementalId);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ColumnFamily> getColumnFamilies() {
        return columnFamilies;
    }

    public void setColumnFamilies(Map<String, ColumnFamily> columnFamilies) {
        this.columnFamilies = columnFamilies;
    }
}
