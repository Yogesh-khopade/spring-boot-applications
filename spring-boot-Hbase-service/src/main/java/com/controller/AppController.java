package com.controller;

/**
 * In this application, I am reading from the HBASE database. As per column and column family, I am reading data
 * from database. 
 * 
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-08 
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barchart.BarchartRecord;
import com.service.HbaseService;

@RestController
public class AppController {	

	public static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Value("${linechart}")
	private String linechartFile;
	
	@Value("${barchart}")
	private String barchartFile;
	
	@Value("${pichart}")
	private String pichartFile;
	
	@Value("${funnel}")
	private String funnelFile;
	
	@Value("${doughnutchart}")
	private String doughnutchartFile;
	
	@Autowired
    private HbaseService hbaseService;
	
	@RequestMapping(value="/linechart", method=RequestMethod.GET, produces="application/json")
	public String getLineChart()
	{
		StringBuilder data = new StringBuilder();
		 FileReader fr = null;
		try {
			fr = new FileReader(linechartFile);
			int i;    
	         while((i=fr.read())!=-1)    
	         data.append((char)i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        finally {
        	try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}   
           
		return data.toString();
	}
	
	@RequestMapping(value="/barchart", method=RequestMethod.GET, produces="application/json")
	public List<BarchartRecord> getBarChart() throws IOException
	{
		return hbaseService.getRecordsFromBarChartTable();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/barchart", consumes="application/json", produces="application/json")
    public void deleteCreateAndPopulateBarChart(@RequestBody List<BarchartRecord> barchartRecordList) throws Exception {
        hbaseService.deleteBarChartTable();
        hbaseService.createBarChartTable();
        hbaseService.populateBarChartTable(barchartRecordList);
    }
	
	@RequestMapping(value="/pichart", method=RequestMethod.GET, produces="application/json")
	public String getPiChart()
	{
		StringBuilder data = new StringBuilder();
		 FileReader fr = null;
		try {
			fr = new FileReader(pichartFile);
			int i;    
	         while((i=fr.read())!=-1)    
	         data.append((char)i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        finally {
        	try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}   
           
		return data.toString();
	}
	
	@RequestMapping(value="/funnel", method=RequestMethod.GET, produces="application/json")
	public String getFunnel()
	{
		StringBuilder data = new StringBuilder();
		 FileReader fr = null;
		try {
			fr = new FileReader(funnelFile);
			int i;    
	         while((i=fr.read())!=-1)    
	         data.append((char)i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        finally {
        	try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}   
           
		return data.toString();
	}
	
	@RequestMapping(value="/doughnutchart", method=RequestMethod.GET, produces="application/json")
	public String getDoughnutChart()
	{
		StringBuilder data = new StringBuilder();
		 FileReader fr = null;
		try {
			fr = new FileReader(doughnutchartFile);
			int i;    
	         while((i=fr.read())!=-1)    
	         data.append((char)i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        finally {
        	try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}   
           
		return data.toString();
	}
}
