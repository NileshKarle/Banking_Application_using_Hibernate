package com.bridgelabz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bank1")
public class BankingDetailsMapping {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;		
	private String bankName;
	private String city;
	private String name;
	
	private int accountNumber;
	/*private int user_id;
*/
	@ManyToOne
	@JoinColumn(name="userMapping_id")
	private UserDetailsMapping userMapping;
	
	
	public UserDetailsMapping getUser() {
		return userMapping;
	}

	public void setUser(UserDetailsMapping userMapping) {
		this.userMapping = userMapping;
	}

	/*public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
