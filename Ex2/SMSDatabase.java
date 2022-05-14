package ex2;

import java.util.*;

public class SMSDatabase {
	private HashSet<Chat> Data = new HashSet<Chat>();
	
	public HashSet<Chat> getData() {
		return this.Data;
	}
	
	public void addChat(Chat chat) {
		Data.add(chat);
	}
	
	public void removeChat(Chat chat) {
		Data.remove(chat);
	}
		
	public void showAllContacts() {
		if(!Data.isEmpty()) {
			Iterator<Chat> it = Data.iterator();
			while (it.hasNext()) {
				Chat i = it.next();
				System.out.print(i.getContact().getName() + " ");
			}
			System.out.println("\n");
		}
		else {
			System.out.println("The Chat list is empty");
		}
	}
	
	public boolean IsContactInData(Contact contact) {
		if(Data.isEmpty()) {
			return false;
		}
		else {
			Iterator<Chat> it = Data.iterator();
			while (it.hasNext()) {
				Chat i = it.next();
				if(contact == i.getContact()) {
					return true;
				}
			}
		}	
		return false;
	}
	
	public Chat returnChatByContact(Contact contact) {
		Iterator<Chat> it = Data.iterator();
		while (it.hasNext()) {
			Chat i = it.next();
			if(contact == i.getContact()) {
				return i;
			}
		}
		return null;
	}
}
