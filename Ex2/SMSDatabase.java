package ex2;

import java.util.*;

public class SMSDatabase {  // this is where all chats are stored
	private HashSet<Chat> data;
	
	
	public SMSDatabase() {
		this.data = new HashSet<Chat>(); //we chose HashSet database because the order of chats is not important and unique 
	}
	
	public HashSet<Chat> getData() { // get all chats
		return this.data;
	}
	
	public void addChat(Chat chat) { // add chat
		data.add(chat);
	}
	
	public void removeChat(Chat chat) { //remove chat
		data.remove(chat);
	}
	
	public boolean isEmpty() { //return true if data is empty
		if(data.isEmpty())
			return true;
		else
			return false;
	}
		
	public void showAllContacts() { //show all contacts corresponding to chats
		if(!data.isEmpty()) {
			Iterator<Chat> it = data.iterator();
			while (it.hasNext()) {
				Chat i = it.next();
				System.out.println(i.getContact().getName());
			}
			System.out.print("\n");
		}
		else {
			System.out.println("No Contacts added in App");
		}
	}
	
	public void showAllChats() { //print all chats
		if(!data.isEmpty()) {
			Iterator<Chat> it = data.iterator();
			while (it.hasNext()) {
				Chat i = it.next();
				System.out.println(i.getContact().getName());
				System.out.println(i.ChatToString());
			}
			System.out.print("\n");
		}
		else {
			System.out.println("The Chat list is empty");
		}
	}
	
	
	public boolean IsContactInData(Contact contact) { //return true if exists a chat corresponding to given contact
		if(data.isEmpty()) {
			return false;
		}
		else {
			Iterator<Chat> it = data.iterator();
			while (it.hasNext()) {
				Chat i = it.next();
				if(contact == i.getContact()) {
					return true;
				}
			}
		}	
		return false;
	}
	
	public Chat returnChatByContact(Contact contact) { //return chat corresponding to given contact
		Iterator<Chat> it = data.iterator();		
		while (it.hasNext()) {
			Chat i = it.next();
			if(contact == i.getContact()) {
				return i;
			}
		}
		return null;
	}
	
    public String printContactsbysentence(String sentence) { //return all contact names that include a given sentence in their chats
    	String res = "";
    	for(Chat chat:data) {
			if(chat.getMessages().contains(sentence)) {
				res = res.concat(chat.getContact().getName());
				res = res.concat("\n");
			}
		}
    	return res;
    }
    
    public void CheckdeletedContacts(PhoneBook phoneBook) { //remove chats of deleted accounts in the PhoneBook
    	
		Iterator<Chat> it = data.iterator();
		while (it.hasNext()) {
			Chat i = it.next();
			if(phoneBook.searchcontact(i.getContact().getName()) == null)
				data.remove(i);
				
		}
    }
}
