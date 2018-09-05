package com.domain;

/**
* @author  Yogesh Khopade
* @version 1.0
* @since   2018-09-07 
*/

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENIE_USER")
public class User {
	@Id
	private String user_Id;
	private String mdm_Id;
	private String cust_Id ;
	private String mobile_No ;
	private String pan_No;
	private String customer_Name;
	private String occupation;
	private String gender;
	private String acc_no;
	private String ckyc_No;
	private String upi;
	private String email;
	private String twitter_Handle;
	private String dob;
	private String address_City;
	private String address_Area;
	private String home_Branch;
	private String asset_RM;
	private String liability_RM;
	private String group_Id;
	private String business_Segment;
	private String partner_Segment;
	private String cust_Open_Date;	

	public String getMdm_Id() {
		return mdm_Id;
	}

	public void setMdm_Id(String mdm_Id) {
		this.mdm_Id = mdm_Id;
	}

	public String getCust_Id() {
		return cust_Id;
	}

	public void setCust_Id(String cust_Id) {
		this.cust_Id = cust_Id;
	}

	public String getMobile_No() {
		return mobile_No;
	}

	public void setMobile_No(String mobile_No) {
		this.mobile_No = mobile_No;
	}

	public String getPan_No() {
		return pan_No;
	}

	public void setPan_No(String pan_No) {
		this.pan_No = pan_No;
	}

	public String getCustomer_Name() {
		return customer_Name;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(String acc_no) {
		this.acc_no = acc_no;
	}

	public String getCkyc_No() {
		return ckyc_No;
	}

	public void setCkyc_No(String ckyc_No) {
		this.ckyc_No = ckyc_No;
	}

	public String getUpi() {
		return upi;
	}

	public void setUpi(String upi) {
		this.upi = upi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwitter_Handle() {
		return twitter_Handle;
	}

	public void setTwitter_Handle(String twitter_Handle) {
		this.twitter_Handle = twitter_Handle;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress_City() {
		return address_City;
	}

	public void setAddress_City(String address_City) {
		this.address_City = address_City;
	}

	public String getAddress_Area() {
		return address_Area;
	}

	public void setAddress_Area(String address_Area) {
		this.address_Area = address_Area;
	}

	public String getHome_Branch() {
		return home_Branch;
	}

	public void setHome_Branch(String home_Branch) {
		this.home_Branch = home_Branch;
	}

	public String getAsset_RM() {
		return asset_RM;
	}

	public void setAsset_RM(String asset_RM) {
		this.asset_RM = asset_RM;
	}

	public String getLiability_RM() {
		return liability_RM;
	}

	public void setLiability_RM(String liability_RM) {
		this.liability_RM = liability_RM;
	}

	public String getGroup_Id() {
		return group_Id;
	}

	public void setGroup_Id(String group_Id) {
		this.group_Id = group_Id;
	}

	public String getBusiness_Segment() {
		return business_Segment;
	}

	public void setBusiness_Segment(String business_Segment) {
		this.business_Segment = business_Segment;
	}

	public String getPartner_Segment() {
		return partner_Segment;
	}

	public void setPartner_Segment(String partner_Segment) {
		this.partner_Segment = partner_Segment;
	}

	public String getCust_Open_Date() {
		return cust_Open_Date;
	}

	public void setCust_Open_Date(String cust_Open_Date) {
		this.cust_Open_Date = cust_Open_Date;
	}
	
	
}
