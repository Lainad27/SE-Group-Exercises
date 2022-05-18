package ex2.SMS;

import java.util.*;

import ex2.PhoneBookPkg.Contact;

public class Chat {
	private Contact contact;
	private ArrayList <String> messages;
	private String chatText; 
	
	public Chat(Contact contact) {
		setContact(contact);  //each chat contains 
		this.messages = new ArrayList<String>(); // we chose arraylist to store the messages because the order is important
		this.chatText = "This is The beginning of the chat with " + contact.getName(); //print this string + conatct's name by default
	}
	
	public void setContact(Contact contact) { //set contact
		this.contact = contact;
	}
	
	public Contact getContact() { //get contact
		return this.contact;
	}
	
	public ArrayList<String> getMessages(){ //get messages from chat
		return messages;
	}
	
	public void addMessage(String message) { //add message to chat
		this.messages.add(message);
	}
	
	public String ChatToString() { //print chat
		String res = "";
		res = res.concat(chatText);
		Iterator<String> it = messages.iterator();
		while (it.hasNext()) {
			String i = it.next();
			res = res.concat("\n");
			res = res.concat(i);
		}
		return res;		
	}
}