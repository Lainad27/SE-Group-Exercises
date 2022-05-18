package ex2.Calendar;

import java.util.*;

import ex2.PhoneBookPkg.Contact;
import ex2.PhoneBookPkg.PhoneBook;

public class Calendar {
	private ArrayList<Event> calendar;
	
	public Calendar() {
		calendar = new ArrayList<Event>();
	}
	
	public void addEvent(Date date, int meetingDuration, String description) { //adding event withuot contact
        Event event = new Event(date, meetingDuration, description);
        calendar.add(event);
        System.out.println("Event added.");
    }
	
	public void addEvent(Date date, int meetingDuration, Contact contact) { //adding event with contact
        Event meeting = new Event(date,meetingDuration,contact);
        calendar.add(meeting);
        System.out.println("Meeting added.");
    }
	
	public void removeEvent (Event eventToDelete) {// delete event
        calendar.remove(eventToDelete);
        System.out.println("Event deleted.");
    }
	
	
	public void printAllEventsNumbered(){ //print all events
        for (int i=0; i<calendar.size(); i++)
            System.out.println(i + " - " + calendar.get(i).toString());
    }
	
	public void deleteEventByIndex (int index){ // deleting event by index in the arrayList
		calendar.remove(index);
        System.out.println("Event deleted.");
    }
	
	public class DateComparator implements Comparator<Event> { //class which compares 2 events
	    public int compare(Event ev1, Event ev2)
        {
            return new Long(ev1.getDate().getTime()).compareTo(ev2.getDate().getTime());
        }
	}

    public void printByDate(int day) { //print all events of a specific date
        ArrayList<Event> sortedByTime = new ArrayList<Event>();
        for (Event event : calendar) {
            if (event.getDate().getDate() == day)
                sortedByTime.add(event);
        }
        if (sortedByTime.isEmpty()) {
    		System.out.println("There are no events on that day.");
    		return;
    	}
        Collections.sort(sortedByTime, new DateComparator());
        for (Event events : sortedByTime)
            events.print();
    }

    public void printByContact(String name){ //print all meetings with contact
        ArrayList<Event> sortedByName= new ArrayList<Event>();
        for (Event event : calendar) {
            if (event.getContact() != null && event.getContact().getName().compareTo(name) == 0)
            	sortedByName.add(event);
        }
        if (sortedByName.isEmpty()) {
    		System.out.println("There are no events with that contact name.");
    		return;
    	}
        Collections.sort(sortedByName, new DateComparator());
        for (Event events : sortedByName)
            events.print();
    }
    public void printAllEvents(){ //print all events in the calander
    	if (calendar.isEmpty()) {
    		System.out.println("There are no saved events.");
    		return;
    	}
        for (Event event: calendar)
            event.print();
    }

	public void CheckdeletedContacts(PhoneBook phoneBook) {
		Iterator<Event> it = calendar.iterator();
		while (it.hasNext()) {
			Event ev = it.next();
			if(ev.getContact()!= null && phoneBook.searchContact(ev.getContact().getName()) == null)
				it.remove();
				
		}
		
	}
	
	public void deleteOverlaps(){ //delete overlaped events
		if (calendar.isEmpty()) {
			System.out.println("The calendar is empty - nothing to delete");
			return;
		}
		Iterator<Event> deleteIt = calendar.iterator();
		
		Event ev = deleteIt.next();
		while (deleteIt.hasNext()) {
			
			long lowerBound = ev.getDate().getTime() + ev.getMeetingDuration();
			ev = deleteIt.next();
			
			if (ev.getDate().getTime() < lowerBound)
				deleteIt.remove();
				if (deleteIt.hasNext())
					ev = deleteIt.next();
		}
		System.out.println("Overlaps deleted.");
    }

	public boolean isEmpty() { // if calender is empty
		return calendar.isEmpty();
	}
}
