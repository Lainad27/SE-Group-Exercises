package ex2.Calendar;

import java.util.Date;

import ex2.App;
import ex2.MobilePhone;
import ex2.MobilePhone.AppPermissionsData;
import ex2.MobilePhone.Permissions;
import ex2.PhoneBookPkg.PhoneBook;

public class CalendarApp extends App {
	
	int year = 2022;
    int month = 5;
	
	private PhoneBook phoneBook;
	private Calendar calendar = new Calendar();
	
	public String getAppName() {
		return "Calendar app";
	}

	public String getOptions() {
		 return "1 - add event to calender\n"
         + "2 - delete event from calender\n"
         + "3 - print all events of a date\n"
         + "4 - print all events with contact\n"
         + "5 - delete overlaps\n"
         + "6 - print all events";		
	}

	public void handleCommand(String command) {
		switch (command) {
		case "1":
            System.out.print("In what day of the month the event will occur? ");
            int day = input.nextInt();
            System.out.print("hour: ");
            int hour = input.nextInt();
            System.out.print("minutes: ");
            int minutes = input.nextInt();
            Date date = new Date(year,month,day,hour,minutes);
            System.out.print("The event's duration(in minutes): ");
            int meetingDuration = 60000*input.nextInt();
            input.nextLine();// consume next line
            if (meetingDuration <= 0 || meetingDuration > 60000*60) {
                System.out.println("Invalid meeting duration.");
                return;
            }
            System.out.print("Do you want to meet with another person?(yes/no) ");
            if(input.nextLine().toLowerCase().compareTo("yes") == 0){
            	if (phoneBook.isEmpty()) {
            		System.out.println("You have no saved contacts.");
            		return;
            	}
            	System.out.println("what's the name of the contact you would like to meet with? options: ");
            	phoneBook.printAllContactNames();
            	String contactName = input.nextLine();
            	if (phoneBook.searchContact(contactName)!= null) 
            		calendar.addEvent(date, meetingDuration, phoneBook.searchContact(contactName)); 
            	else {
            		System.out.println("Invalid contact name.");
            		return;
            	}
            }
            else{
                System.out.print("what's the description of the event? ");
                String description = (input.nextLine());
                calendar.addEvent(date,meetingDuration,description);
            }
            break;
        case "2":
        	if (calendar.isEmpty()) {
        		System.out.println("You have no saved events.");
        		return;
        	}
        	System.out.println("Choose one of the following events (enter the number next to it): ");
        	calendar.printAllEventsNumbered();
        	int index = input.nextInt();
        	input.nextLine();// consume next line
        	calendar.deleteEventByIndex(index);
            break;
        case "3":
            System.out.print("what day to print? ");
            day = input.nextInt();
            input.nextLine(); //consume next line
            calendar.printByDate(day);
            break;
        case "4":
        	if (phoneBook.isEmpty()) {
        		System.out.println("You have no saved contacts.");
        		return;
        	}
            System.out.println("what's the name of the contact you would like to print the meetings with? options:");
            phoneBook.printAllContactNames();
            String cntName = input.nextLine();
            calendar.printByContact(cntName);
            break;
        case "5":
        	calendar.deleteOverlaps();
            break;
        case "6":
        	calendar.printAllEvents();
            break;
        default:
            System.out.println("Invalid command. Please enter \"h\" to see command options.");
            break;
		}
		
	}

	public void init(AppPermissionsData data) {
		phoneBook = (PhoneBook) data.getDataFromPerm(Permissions.PHONEBOOK);
		calendar.CheckdeletedContacts(phoneBook);
	}
	

}
