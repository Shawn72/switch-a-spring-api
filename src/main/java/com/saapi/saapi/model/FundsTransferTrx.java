package com.saapi.saapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class FundsTransferTrx {
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private int id;
     
     @Column(name="account_from")
     private String account_from;
     
     public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount_from() {
		return account_from;
	}

	public void setAccount_from(String account_from) {
		this.account_from = account_from;
	}

	public String getAccount_to() {
		return account_to;
	}

	public void setAccount_to(String account_to) {
		this.account_to = account_to;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="account_to")
     private String account_to;
     
     @Column(name="amount")
     private double amount; 	 

}
