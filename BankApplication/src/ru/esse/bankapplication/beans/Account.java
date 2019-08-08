package ru.esse.bankapplication.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
public class Account {

	public static final String STATUS_OPEN ="open";
	public static final String STATUS_CLOSED = "closed";
	public static final String STATUS_SUSPEND = "suspend";
	
	private String id;
	private String balance;
	private String open_date;
	private String close_date;
	private String status;
	private String client_id;
	
	public Account() {
	}

	public Account(String id, String balance, String open_date, String close_date, String status, String client_id) {
		this.id = id;
		this.balance = balance;
	    this.open_date = open_date;
	    this.close_date = close_date;
	    this.status = status;
	    this.client_id = client_id;
	   }
	
	public Account(String balance, String open_date, String close_date, String status, String client_id) {
		this.balance = balance;
	    this.open_date = open_date;
	    this.close_date = close_date;
	    this.status = status;
	    this.client_id = client_id;
	   }	
	
	public String getId() {
		return id;
	}
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	public String getBalance() {
		return balance;
	}
	@XmlElement
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getOpen_date() {
		return open_date;
	}
	@XmlElement
	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}
	public String getClose_date() {
		return close_date;
	}
	@XmlElement
	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}
	public String getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClient_id() {
		return client_id;
	}
	@XmlElement
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
}
