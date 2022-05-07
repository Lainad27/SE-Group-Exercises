package ex2;

import java.util.Scanner;

public class MobilePhone extends App {
	static Scanner input = new Scanner(System.in);
	
	CalendarApp calender;
	MediaApp media;
	SMSApp sms;
	PhoneBookApp phoneBook;
	
	public String getAppName() {
		return "Phone";
	}
	
	public MobilePhone() {
		calender = new CalendarApp();
		media = new MediaApp();
		sms = new SMSApp();
		phoneBook = new PhoneBookApp();
	}
	
	public void printOptions() {
		System.out.println("CALENDER - enter calender app");
		System.out.println("MEDIA - enter media app");
		System.out.println("SMS - enter SMS app");
		System.out.println("PHONEBOOK - enter phoneBook app");
		System.out.println("CONTENTS - Print the commands of all the apps");
	}

	public void handleCommand(String command) {
		switch (command) {
		case "CALENDER":
			calender.run();
			break;
		case "MEDIA":
			media.run();
			break;
		case "SMS":
			sms.run();
			break;
		case "PHONEBOOK":
			phoneBook.run();
			break;
		case "CONTENTS":
			printAllApps();
			break;
		default:
			System.out.println("Invalid command. Please enter \"h\" to see command options.");
			break;
		}
		
	}
	
	
	public void printAllApps() {
		calender.printOptions();
		media.printOptions();
		sms.printOptions();
		phoneBook.printOptions();
	}
	


}
