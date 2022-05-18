package ex2;

import java.util.Hashtable;
import java.util.Scanner;

import ex2.Calendar.CalendarApp;
import ex2.Media.MediaApp;
import ex2.PhoneBookPkg.PhoneBook;
import ex2.PhoneBookPkg.PhoneBookApp;
import ex2.SMS.SMSApp;


public class MobilePhone {
	static Scanner input = new Scanner(System.in);
	

	Hashtable<App, AppPermissionsData> appToPermissions;
	
	PhoneBook phoneBook = new PhoneBook(); //The phonebook is stored here
	
	public CalendarApp calenderApp; // all of our apps are fields
	public MediaApp mediaApp;
	public SMSApp smsApp;
	public PhoneBookApp phoneBookApp;
	
	public enum Permissions{ //enums of app permissions - for example the calendar may need the PHONEBOOK permission
		PHONEBOOK
	}
	
	public String getAppName() {
		return "Phone";
	}
	
	public class AppPermissionsData { //inner class for app permissions. we won't really use it but maybe a developer in the future will add other permissions like location
		Hashtable<Permissions, Object> permissions;
		private void addPermission(Permissions PermName, Object data) {
			permissions.put(PermName, data);
		}
		
		private void removePermission(Permissions PermName) {
			permissions.remove(PermName);
		}
		
		private AppPermissionsData() {
			permissions =  new Hashtable<Permissions, Object>();
		}
		
		public Object getDataFromPerm (Permissions PermName) {
			return permissions.get(PermName);
		}
		
	}
	public MobilePhone() { //initialize all apps and give sum of them the phonebook permission
		calenderApp = new CalendarApp();
		mediaApp = new MediaApp();
		smsApp = new SMSApp();
		phoneBookApp = new PhoneBookApp();
		appToPermissions = new Hashtable<App, AppPermissionsData>();
		appToPermissions.put(calenderApp, new AppPermissionsData());
		appToPermissions.put(mediaApp, new AppPermissionsData());
		appToPermissions.put(smsApp, new AppPermissionsData());
		appToPermissions.put(phoneBookApp, new AppPermissionsData());
		
		appToPermissions.get(calenderApp).addPermission(Permissions.PHONEBOOK, phoneBook);
		appToPermissions.get(smsApp).addPermission(Permissions.PHONEBOOK, phoneBook);
		appToPermissions.get(phoneBookApp).addPermission(Permissions.PHONEBOOK, phoneBook);
	}
	
	public void printOptions() { //phone options
		System.out.println("CALENDAR - enter calender app");
		System.out.println("MEDIA - enter media app");
		System.out.println("SMS - enter SMS app");
		System.out.println("PHONEBOOK - enter phoneBook app");
		System.out.println("CONTENTS - Print the commands of all the apps");
		System.out.println("OFF - turn off the phone");
	}

	public void handleCommand(String command) {
		switch (command) {
		case "calendar":
			calenderApp.run(appToPermissions.get(calenderApp));
			break;
		case "media":
			mediaApp.run(appToPermissions.get(mediaApp));
			break;
		case "sms":
			smsApp.run(appToPermissions.get(smsApp));
			break;
		case "phonebook":
			phoneBookApp.run(appToPermissions.get(phoneBookApp));
			break;
		case "contents":
			printAllApps();
			break;
		default:
			System.out.println("Invalid command. Please enter \"h\" to see command options.");
			break;
		}
		
	}
	
	
	public void printAllApps() {// gets options from all apps and prints them
		System.out.println(calenderApp.getAppName() + ":");
		System.out.println(calenderApp.getOptions());
		System.out.println(mediaApp.getAppName() + ":");
		System.out.println(mediaApp.getOptions());
		System.out.println(smsApp.getAppName() + ":");
		System.out.println(smsApp.getOptions());
		System.out.println(phoneBookApp.getAppName() + ":");
		System.out.println(phoneBookApp.getOptions());
	}
public void turnOn() { //main program loop for command input
		System.out.println("Entered " + getAppName() + "!");
		boolean phoneOn = true;
		while (phoneOn) {
			System.out.println("What command would you like to execute? \n\"help\" to see options, \"OFF\" to turn off phone");
			System.out.print("> ");
			String command = input.nextLine().toLowerCase();
			
			switch (command) {
			case "help":
			case "h":
				printOptions();
				break;
			case "leave":
			case "off":
			case "l":
			case "o":
				phoneOn=false;
				break;
			default:
				handleCommand(command);
				break;
			}
		}
	}

	


}
