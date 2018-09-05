package com.barchart;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

import com.model.Column;
import com.model.ColumnFamily;
import com.utils.HbaseUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BarChartHbaseTableUtils {
    private Admin admin;
    private Connection connection;

    public static final String BARCHART_TABLENAME = "barchart";
    public static final String SINGLE_CF_NAME = "singleCf";
    public static final String NAME_COL_NAME = "name";
    public static final String YVAL_COL_NAME = "yVal";

    public BarChartHbaseTableUtils(Admin admin, Connection connection){
        this.admin = admin;
        this.connection = connection;
    }

    public void deleteBarChartTable() throws IOException {
        HbaseUtils.deleteTable(getAdmin(), BARCHART_TABLENAME);
    }

    public void createBarChartTable() throws IOException {
        List<String> cfNames = new ArrayList<>();
        cfNames.add("singleCf");
        HbaseUtils.createTable(getAdmin(), BARCHART_TABLENAME, cfNames);
    }

    public void populateBarChartTable(List<BarchartRecord> barchartRecords) throws IOException {
        Table table = connection.getTable(TableName.valueOf(BARCHART_TABLENAME));
        int id = 1;
        for(BarchartRecord barchartRecord : barchartRecords){
            BarchartHbaseTableRecord barchartHbaseTableRecord = BarchartHbaseTableRecord.getBarChartHbaseTableRecord(barchartRecord);
            byte[] rowKey = barchartHbaseTableRecord.getRowKeyForRecord(barchartHbaseTableRecord, id);
            barchartHbaseTableRecord.insertRecord(admin, rowKey, barchartHbaseTableRecord, table);
            id=id+1;
        }
    }

    public List<BarchartRecord> getRecordsFromBarChartTable() throws IOException {
        List<BarchartRecord> barchartRecords = new ArrayList<>();
        Table table = connection.getTable(TableName.valueOf(BARCHART_TABLENAME));
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);

        for (Result result = scanner.next(); (result != null); result = scanner.next()) {
            Get g = new Get(result.getRow());
            Result r = table.get(g);

            BarchartHbaseTableRecord barchartHbaseTableRecord = new BarchartHbaseTableRecord();
            Map<String, ColumnFamily> columnFamilies = barchartHbaseTableRecord.getColumnFamilies();
            for(Map.Entry<String, ColumnFamily> entry : columnFamilies.entrySet()){
                ColumnFamily columnFamily = entry.getValue();
                Map<String, Column> columns = columnFamily.getColumnMap();
                for(Map.Entry<String, Column> entry1 : columns.entrySet()){
                    Column column = entry1.getValue();
                    byte[] colValue = r.getValue(Bytes.toBytes(columnFamily.getName()), Bytes.toBytes(column.getName()));
                    column.setValue(colValue);
                }
            }
            barchartRecords.add(BarchartHbaseTableRecord.getBarChartRecord(barchartHbaseTableRecord));
        }
        return barchartRecords;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

