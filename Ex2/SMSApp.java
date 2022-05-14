package ex2;

//import java.util.ArrayList;

import ex2.MobilePhone.AppPermissionsData;
import ex2.MobilePhone.Permissions;

public class SMSApp extends App {
	
	public String getAppName() {
		return "SMS app";
	}
	
	PhoneBook phoneBook;
	SMSDatabase Data = new SMSDatabase();

	public void printOptions() {
		// TODO Auto-generated method stub
		System.out.println("1 - Add chatting to contact (you can have no more than one chat with a contact).");
		System.out.println("2 - Delete chatting with contact.");
		System.out.println("3 - Show chatting with chosen contact.");
		System.out.println("4 - Find sentence in all chats (return list of all contacts, having at least one instance of the sentence in chat with them).");
		System.out.println("5 - Show all chattings.");	
	}

	public void handleCommand(String command) {
		// TODO Auto-generated method stub
		switch (command) {
		case "1":
			System.out.println("Choose a Contact to chat with from the following contacts:");
			phoneBook.printAllContacts();
			String name = input.next();
			Contact contact = phoneBook.searchcontact(name);
			if (contact == null) {
				System.out.println("No such contact exists. Create the contact first");
			}
			else {
				System.out.print("Enter The Message: ");
				String message = input.next();
				if(Data.IsContactInData(contact)) {
					Data.returnChatByContact(contact).addMessage(message);
				}
				else {
					Chat chat = new Chat(contact);
					chat.addMessage(message);
					Data.addChat(chat);
				}
			}		
			break;
			
		case "2":
			System.out.println("Choose a Contact to delete chat with from the following contacts");
			phoneBook.printAllContacts();
			String cntName = input.next();
			Contact cntContact = phoneBook.searchcontact(cntName);
			if (Data.IsContactInData(cntContact)) {
				Chat cntChat = Data.returnChatByContact(cntContact);
				Data.removeChat(cntChat);
			}
			else {
				System.out.println("No such contact exists. Create the contact first");
			}
			
			break;
			
		case "3":
			System.out.println("Choose a Contact to chat with from the following contacts:");
			phoneBook.printAllContacts();
			String cntName3 = input.next();
			Contact contact3 = phoneBook.searchcontact(cntName3);
			if (contact3 == null) {
				System.out.println("No such contact exists. Create the contact first");
			}
			else {
				System.out.println(Data.returnChatByContact(contact3).ChatToString());
			}
			break;
			
		case "4":
			System.out.println("Please insert what sentence you want to find");
			String sentence = input.next();
			for(Chat chat:Data.getData()) {
				if(chat.getMessages().contains(sentence)) {
					System.out.println(chat.getContact().getName());
				}
			}
			break;
		
		case "5":
			
			for (Chat chat:Data.getData()) {
				System.out.println(chat.ChatToString());
				System.out.print("\n");
			}
			break;
			
		default:
			System.out.println("Invalid command. Please enter \"h\" to see command options.");
			break;
		} 	
	}
	
	public void init(AppPermissionsData data) {
		phoneBook = (PhoneBook) data.getDataFromPerm(Permissions.PHONEBOOK);
		
	}
}
