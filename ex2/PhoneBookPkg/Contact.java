package ex2.PhoneBookPkg;

import java.util.*;

public class Contact {
	private String name;
	private String phoneNumber;
	
	public Contact(String name, String phoneNumber) {
		setName(name);
		setPhoneNumber(phoneNumber);
	}
	
	public Contact() {
		setName("Unknown");
		setPhoneNumber("999999999");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoneNumber(String phoneNumber2) {
		this.phoneNumber = phoneNumber2;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String toString() {
		return this.getPhoneNumber() + "," + this.getName();
	}
	public void print() {
		System.out.println(this.toString());
	}
}

