package ex2;
import java.util.*;
import ex1.Contact;

public class MainOfCalandar {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int year = 2022;
        int month = 5;
        ArrayList<Event> calender = new ArrayList<Event>();
        LinkedList<Contact> phoneBook = new LinkedList<Contact>();
        boolean programEnded = false;
        while (!programEnded) {
            System.out.println("What command would you like to execute? \n\"help\" to see options");
            System.out.print("> ");
            String command = input.next();
            switch (command) {
                case "help":
                case "h":
                    System.out.println("1 - add event to calender");
                    System.out.println("2 - delete event from calender");
                    System.out.println("3 - print all events of a date");
                    System.out.println("4 - print all events with contact");
                    System.out.println("5 - delete overlaps");
                    System.out.println("6 - print all events");
                    System.out.println("7 - exit the program");
                    break;
                case "1":
                    System.out.print("In what day of the month the event will occur? ");
                    int day = input.nextInt();
                    System.out.print("hour:");
                    int hour = input.nextInt();
                    System.out.print("minutes:");
                    int minutes = input.nextInt();
                    Date date = new Date(year,month,day,hour,minutes);
                    System.out.print("The event's duration:");
                    int meetingDuration = input.nextInt();
                    System.out.print("Do you want to meet with another person?");
                    if(input.next() == "yes" || input.next() == "Yes"){
                    System.out.print("what's the name of the contact you would like to meet with? ");
                    String newContactName = input.next();
                    System.out.print("And whats their phonenumber? ");
                    String newContactNumber = (input.next());
                    Contact contact = new Contact(newContactName,newContactNumber);
                    addEvent(date,meetingDuration,contact,calender,phoneBook);
                    }
                    else{
                        System.out.print("what's the description of the event?");
                        String description = (input.next());
                        addEvent(date,meetingDuration,description,calender);
                    }
                    break;
                case "2":System.out.print("In what day of the month the event will occur? ");
                    day = input.nextInt();
                    System.out.print("hour:");
                    hour = input.nextInt();
                    System.out.print("minutes:");
                    minutes = input.nextInt();
                    date = new Date(year,month,day,hour,minutes);
                    System.out.print("The event's duration:");
                    meetingDuration = Integer.valueOf(input.next());
                    System.out.print("Do you want to meet with another person?");
                    if(input.next() == "yes" || input.next() == "Yes"){
                        System.out.print("what's the name of the contact you would like to meet with? ");
                        String newContactName = input.next();
                        System.out.print("And whats their phonenumber? ");
                        String newContactNumber = (input.next());
                        Contact contact = new Contact(newContactName,newContactNumber);
                        deleteEvent(date,meetingDuration,contact,calender);
                    }
                    else{
                        System.out.print("what's the description of the event?");
                        String description = (input.next());
                        deleteEvent(date,meetingDuration,description,calender);
                    }
                    break;
                case "3":
                    System.out.print("what day to print?");
                    day = input.nextInt();
                    printByDate(day,calender);
                    break;
                case "4":
                    System.out.print("what's the name of the contact you would like to print the meetings with? ");
                    String cntName = input.next();
                    printByContact(cntName,calender);
                    break;
                case "5":
                    deleteOverlaps(calender);
                    break;
                case "6":
                    printAllEvents(calender);
                    break;
                case "7":
                    System.out.println("Exiting program . . .");
                    programEnded = true;
                    break;
                default:
                    System.out.println("Invalid command. Please enter \"h\" to see command options.");
                    break;
            }
        }
    }

    public static void addEvent(Date date, int meetingDuration, String description, ArrayList<Event> calendar) { //1 add contact to phone book from phone number nad name
        if (meetingDuration <= 0 || meetingDuration >60) {
            System.out.println("Invalid meeting duration.");
            return;
        }
        Event event = new Event(date,meetingDuration,description);
        calendar.add(event);
        System.out.println("event added.");
    }

    public static void addEvent(Date date, int meetingDuration, Contact contact, ArrayList<Event> calendar,LinkedList<Contact> phoneBook) { //1 add contact to phone book from phone number nad name
        if (meetingDuration <= 0 || meetingDuration >60) {
            System.out.println("Invalid meeting duration.");
            return;
        }
        if(ex1.Main.searchcontact(contact.getName(),phoneBook) == null){
            System.out.println("Invalid contact.");
            return;
        }
        Event meeting = new Event(date,meetingDuration,contact);
        calendar.add(meeting);
        System.out.println("meeting added.");
    }

    public static void deleteEvent (Date date, int meetingDuration, String description, ArrayList<Event> calendar) {//2 delete contact by name
        boolean exists = false;
        Event e = new Event(date, meetingDuration,description);
        for(Event ev: calendar){
            if(ev != null)
                if(ev.getMeetingDuration()== (meetingDuration) && ev.getDate().compareTo(date) == 0 && ev.getDescription().compareTo(description) == 0)
                    exists = true;
        }
        if (exists)
            calendar.remove(e);
        System.out.println("Event deleted.");
    }

    public static void deleteEvent (Date date, int meetingDuration, Contact contact, ArrayList<Event> calendar) {//2 delete contact by name
        boolean exists = false;
        Event m = new Event(date,meetingDuration,contact);
        for(Event meeting: calendar){
            if(meeting.getMeetingDuration()== (meetingDuration) && meeting.getDate().compareTo(date) == 0 && meeting.getContact().getName().compareTo(contact.getName()) == 0 && meeting.getContact().getPhoneNumber().compareTo(contact.getPhoneNumber()) == 0)
                exists = true;
                break;
        }
        if (exists)
            calendar.remove(m);
        System.out.println("meeting deleted.");
    }

    public static void printByDate(int day, ArrayList<Event> calendar) {
        ArrayList<Event> sortedByTime = new ArrayList<Event>();
        for (Event event : calendar) {
            if (event.getDate().getDate() == day)
                sortedByTime.add(event);
        }
        for (int i = 0; i < sortedByTime.size(); i++) { //Bubble sort
            for (int j = 0; j < sortedByTime.size() - i - 1; j++) {
                if (sortedByTime.get(j).getDate().getTime() > sortedByTime.get(j + 1).getDate().getTime()){
                    Collections.swap(sortedByTime, j, j+1);
                }
            }
        }
        for (Event events : sortedByTime)
            events.print();
    }

    public static void printByContact(String name, ArrayList<Event> calendar){
        ArrayList<Event> sortedByTime = new ArrayList<Event>();
        for (Event event : calendar) {
            if (event.getContact().getName() == name)
                sortedByTime.add(event);
        }
        for (int i = 0; i < sortedByTime.size(); i++) { //Bubble sort
            for (int j = 0; j < sortedByTime.size() - i - 1; j++) {
                if (sortedByTime.get(j).getDate().getTime() > sortedByTime.get(j + 1).getDate().getTime()){
                    Collections.swap(sortedByTime, j, j+1);
                }
            }
        }
        for (Event events : sortedByTime)
            events.print();
    }

    public static void deleteOverlaps(ArrayList<Event> calendar){
        for(Event event1: calendar) {
            long meetingDuration1 = event1.getMeetingDuration();
            long event1FinishTime = meetingDuration1 + event1.getDate().getTime();
            for (Event event2 : calendar){
                long meetingDuration2 = event2.getMeetingDuration();
                long event2FinishTime = meetingDuration1 + event2.getDate().getTime();
                if(event1FinishTime>event2FinishTime)
                    if (event1.getDate().getTime()<event2.getDate().getTime()) {
                        if (event2.getDescription() != null)
                            deleteEvent(event2.getDate(), event2.getMeetingDuration(), event2.getDescription(), calendar);
                        else if (event2.getContact() != null)
                            deleteEvent(event2.getDate(), event2.getMeetingDuration(), event2.getContact(), calendar);
                    }
                if (event1.getDate().getTime()>event2.getDate().getTime()) {
                    if (event1.getDescription() != null)
                        deleteEvent(event1.getDate(), event1.getMeetingDuration(), event1.getDescription(), calendar);
                    else if (event1.getContact() != null)
                        deleteEvent(event1.getDate(), event1.getMeetingDuration(), event1.getContact(), calendar);
                }

            }
        }

    }

    public static void printAllEvents(ArrayList<Event> calendar){
        for (Event event: calendar)
            event.print();
    }
}
