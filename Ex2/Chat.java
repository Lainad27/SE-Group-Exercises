package ex2;

import java.util.*;

public class Chat {
	private Contact contact = new Contact();
	private ArrayList <String> messages = new ArrayList<String>();
	private String chatText = "This is The beginning of the chat with " + contact.getName(); 
	
	public Chat(Contact contact) {
		setContact(contact);
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Contact getContact() {
		return this.contact;
	}
	
	public ArrayList<String> getMessages(){
		return messages;
	}
	
	public void addMessage(String message) {
		this.messages.add(message);
	}
	
	public String ChatToString() {
		Iterator<String> it = messages.iterator();
		while (it.hasNext()) {
			String i = it.next();
			chatText.concat("\n");
			chatText.concat(i);
		}
		return chatText;		
	}
}
