package ex2.Calendar;
import java.util.*;

import ex2.PhoneBookPkg.Contact;
@SuppressWarnings("deprecation")
public class Event {
    private String description;
    private Date date;
    private int meetingDuration;
    private Contact contact;
    
	public Event(Date date, int meetingDuration, String description) {
		this.date = new Date();
        this.date.setDate(date.getDate());
        this.date.setHours(date.getHours());
        this.date.setMinutes(date.getMinutes());
        this.date.setSeconds(0);
        setMeetingDuration(meetingDuration);
        setDescription(description);
    }

    public Event(Date date, int meetingDuration, Contact contact) {
    	this.date = new Date();
        this.date.setDate(date.getDate());
        this.date.setHours(date.getHours());
        this.date.setMinutes(date.getMinutes());
        this.date.setSeconds(0);
        setMeetingDuration(meetingDuration);
        this.contact = contact;
    }


    public Event() {
    	this.date = new Date();
        this.date.setDate(4);
        this.date.setHours(12);
        this.date.setMinutes(30);
        setMeetingDuration(1);
        setDescription(null);
        setContact(null);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setMeetingDuration(int meetingDuration) {
        this.meetingDuration = meetingDuration;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getMeetingDuration() {
        return this.meetingDuration;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return this.contact;
    }

    public String getDescription() {
        return this.description;
    }
    public String toString() {
        if (this.description!=null)
            return "Time of the event's: " + this.date.toString() + ", The event's duration: " + this.getMeetingDuration()/60000 + " minutes, The event's description: " + this.description;
        return "Time of the meeting: " + this.date.toString() + ", The meeting duration: " + this.getMeetingDuration()/60000 + " minutes, The meeting is with: " + this.contact.getName();
    }
    public void print() {
        System.out.println(this.toString());
    }
}