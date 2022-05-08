package ex2;

import java.util.ArrayList;

import ex1.Contact;
import ex2.MobilePhone.AppPermissionsData;
import ex2.MobilePhone.Permissions;

public class CalendarApp extends App {
	
	PhoneBook phoneBook;
	
	public String getAppName() {
		return "Calendar app";
	}

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
