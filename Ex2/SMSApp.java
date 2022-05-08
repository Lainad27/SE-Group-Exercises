package ex2;

import java.util.ArrayList;

import ex1.Contact;
import ex2.MobilePhone.AppPermissionsData;
import ex2.MobilePhone.Permissions;

public class SMSApp extends App {
	
	public String getAppName() {
		return "SMS app";
	}
	
	PhoneBook phoneBook;

	public void printOptions() {
		// TODO Auto-generated method stub
		
	}

	public void handleCommand(String command) {
		// TODO Auto-generated method stub
		
	}

	public void init(AppPermissionsData data) {
		phoneBook = (PhoneBook) data.getDataFromPerm(Permissions.PHONEBOOK);
		
	}
	

}
