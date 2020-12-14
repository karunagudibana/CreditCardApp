package com.cred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity  
@Table  
public class Cred {
	
	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer credId;
	
	@Column(name = "credNum")
	@NotNull
	@Size(min = 16,max = 16, message = "Card number should have 10 characters")
	private String credNum;
	
	@Column(name = "givenName") 
	@NotNull
	@NotBlank
	private String givenName;
	
	@Column(name="balance")
	private String balance;
	
	@Column(name="cardLimit")
	private String cardLimit;
	
	public Integer getCredId() {
		return credId;
	}
	public void setCredId(Integer credId) {
		this.credId = credId;
	}
	public String getCredNum() {
		return credNum;
	}
	public void setCredNum(String credNum) {
		this.credNum = credNum;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(String cardLimit) {
		this.cardLimit = cardLimit;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	
	

}
