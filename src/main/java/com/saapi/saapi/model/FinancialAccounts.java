package com.saapi.saapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financial_accounts")
public class FinancialAccounts {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="id")
	 private int id;
	 
	 @Column(name="user_id")
	 private String user_id;
	 
	 @Column(name="account_number")
	 private String account_number;   
	 
    
	 public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getAccount_number() {
		return account_number;
	}
	
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	

}
