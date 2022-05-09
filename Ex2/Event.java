package ex2;
import ex1.Contact;

import java.util.*;
public class Event {
    private String description;
    private Date date;
    private int meetingDuration;

    private Contact contact;
    public Event(Date date, int meetingDuration, String description) {
        date.setDate(date.getDate());
        date.setHours(date.getHours());
        date.setMinutes(date.getMinutes());
        setMeetingDuration(meetingDuration);
        setDescription(description);
    }

    public Event(Date date, int meetingDuration, Contact contact) {
        date.setDate(date.getDate());
        date.setHours(date.getHours());
        date.setMinutes(date.getMinutes());
        setMeetingDuration(meetingDuration);
        contact.setName(contact.getName());
        contact.setPhoneNumber(contact.getPhoneNumber());
    }


    public Event() {
        date.setDate(4);
        date.setHours(12);
        date.setMinutes(30);
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
            return " Time of the event's: " + this.date.toString() + ", The event's duration:" + this.getMeetingDuration() + ", The event's description" + this.description;
        else if(this.contact!=null)
            return " Time of the meeting: " + this.date.toString() + ", The meeting duration:" + this.getMeetingDuration() + ", The meeting is with: " + this.contact.getName();
            return null;
    }
    public void print() {
        System.out.println(this.toString());
    }
}
