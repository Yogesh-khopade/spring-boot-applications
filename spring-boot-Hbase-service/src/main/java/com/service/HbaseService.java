package com.service;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

import com.barchart.BarChartHbaseTableUtils;
import com.barchart.BarchartRecord;
import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class HbaseService {
    private Configuration configuration;
    private Connection connection;
    private Admin admin;
    private BarChartHbaseTableUtils barChartHbaseTableUtils;

    public HbaseService() throws IOException, ServiceException{
        initialize();
        if(!checkHbaseConnetivity()){
            System.out.println("Hbase is not accesible");
            System.exit(1);
        }else{
            System.out.println("Hbase is accesible");
        }

        connection = getConnection();
        System.out.println("Got connection to hbase");

        admin = connection.getAdmin();
        System.out.println("Got Admin to hbase");

        barChartHbaseTableUtils = new BarChartHbaseTableUtils(admin, connection);
    }

    public void deleteBarChartTable() throws IOException {
        System.out.println("Deleting table : " + BarChartHbaseTableUtils.BARCHART_TABLENAME);
        barChartHbaseTableUtils.deleteBarChartTable();
    }

    public void createBarChartTable() throws IOException {
        System.out.println("Creating table : " + BarChartHbaseTableUtils.BARCHART_TABLENAME);
        barChartHbaseTableUtils.createBarChartTable();
    }

    public void populateBarChartTable(List<BarchartRecord> barchartRecordList) throws IOException {
        System.out.println("Inserting data in table : " + BarChartHbaseTableUtils.BARCHART_TABLENAME);
        barChartHbaseTableUtils.populateBarChartTable(barchartRecordList);
    }

    public List<BarchartRecord> getRecordsFromBarChartTable() throws IOException {
        System.out.println("Getting data from : " + BarChartHbaseTableUtils.BARCHART_TABLENAME);
        List<BarchartRecord> barchartRecords = barChartHbaseTableUtils.getRecordsFromBarChartTable();

        return barchartRecords;
    }

    private void initialize(){
        configuration = HBaseConfiguration.create();
        configuration.clear();
        configuration.set("hbase.zookeeper.quorum", "localhost");
        configuration.set("hbase.zookeeper.property.clientPort","2181");
    }

    private boolean checkHbaseConnetivity() throws IOException, ServiceException {
        try {
            HBaseAdmin.checkHBaseAvailable(configuration);
            return true;
        } catch (MasterNotRunningException e) {
            System.out.println("HBase is not running." + e.getMessage());
            return false;
        }
    }

    private Connection getConnection() throws IOException {
        return ConnectionFactory.createConnection(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
