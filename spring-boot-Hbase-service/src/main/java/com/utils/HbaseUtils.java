package com.utils;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.List;

public class HbaseUtils {
    public static void deleteTable(Admin admin, String tableName) throws IOException {
        TableName table = TableName.valueOf(tableName);

        if (admin.tableExists(table)) {
            admin.disableTable(table);
            admin.deleteTable(table);
        }
    }

    public static void createTable(Admin admin, String tableName, List<String> cfNames) throws IOException {
        TableName table = TableName.valueOf(tableName);
        HTableDescriptor desc = new HTableDescriptor(table);

        for(String cfName : cfNames){
            desc.addFamily(new HColumnDescriptor(cfName));
        }

        admin.createTable(desc);
    }
}
