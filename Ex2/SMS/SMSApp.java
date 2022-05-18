package ex2.SMS;

import ex2.App;
import ex2.MobilePhone;
import ex2.MobilePhone.AppPermissionsData;
import ex2.MobilePhone.Permissions;
import ex2.PhoneBookPkg.Contact;
import ex2.PhoneBookPkg.PhoneBook;

public class SMSApp extends App { 
	
	public String getAppName() {
		return "SMS app";
	}
	
	PhoneBook phoneBook;
	SMSDatabase database = new SMSDatabase(); //load data - including all chats

	public String getOptions() {
		return "1 - Add chat to contact (you can have no more than one chat with a contact).\n"
		+ "2 - Delete chat with contact.\n"
		+ "3 - Show chat with chosen contact.\n"
		+ "4 - Find sentence in all chats (return list of all contacts, having at least one instance of the sentence in chat with them).\n"
		+ "5 - Show all chats.";	
	}

	public void handleCommand(String command) {
		switch (command) {
		case "1":
			System.out.println("Choose a Contact to chat with from the following contacts:");
			phoneBook.printAllContacts(); //show to user all options to choose from
			String name = input.nextLine();//gets contact name
			Contact contact = phoneBook.searchContact(name);//find contact by contact's name corresponding to phoneBook 
			if (contact == null) { 
				System.out.println("No such contact exists. Create the contact first"); //in case that contact not found in phoneBook 
			}
			else {
				System.out.print("Enter The Message: ");
				String message = input.nextLine(); //gets message to add to chat
				if(database.IsContactInData(contact)) {
					database.returnChatByContact(contact).addMessage(message); //add message to chat with contact
				}
				else { //in case that contact exist in phoneBook but not in Data (no chat exists with the contact)
					Chat chat = new Chat(contact);
					chat.addMessage(message);//add message to chat with contact
					database.addChat(chat);//add the new chat to data
				}
			}		
			break;
			
		case "2":
			System.out.println("Choose a Contact to delete chat with from the following contacts");
			phoneBook.printAllContacts();//show to user all options to choose from
			String cntName = input.nextLine();//gets contact name
			Contact cntContact = phoneBook.searchContact(cntName);//find contact by contact's name corresponding to phoneBook
			if (database.IsContactInData(cntContact)) {
				Chat cntChat = database.returnChatByContact(cntContact);//return chat by given contact
				database.removeChat(cntChat);//delete chat with the contact
			}
			else {
				System.out.println("No such contact exists. Create the contact first");//in case that contact not found in phoneBook
			}
			
			break;
			
		case "3":
			if(!database.isEmpty()) {
				System.out.println("Choose a Contact to print their messages from the following contacts:");
				database.showAllContacts();//show to user all options to choose from
				String cntName3 = input.nextLine();//gets contact name
				Contact contact3 = phoneBook.searchContact(cntName3);//find contact by contact's name corresponding to phoneBook
				if (contact3 == null) {
					System.out.println("No such contact exists. Create the contact first");//in case that contact not found in phoneBook
				}
				else {
					if(database.IsContactInData(contact3)) {
						System.out.println(database.returnChatByContact(contact3).ChatToString());//print chat by given contact
					}
					else {
						System.out.println("Add chat with contact first");//in case that contact exist in phoneBook but not in Data (no chat exists with the contact)
					}
				}
			}
			else {
				System.out.println("No SMS chat found. Please create one first");//in case that data is empty
			}
			
			break;
			
		case "4":
			System.out.println("Please insert what sentence you want to find:");
			String sentence = input.nextLine();//get sentence
			System.out.println(database.printContactsbysentence(sentence));//return all contact names that include a given sentence in their chats
			break;
		
		case "5":			
			database.showAllChats();//print all chats
			break;
			
		default:
			System.out.println("Invalid command. Please enter \"h\" to see command options.");//invalid command
			break;
		} 	
	}
	
	public void init(AppPermissionsData data) {
		phoneBook = (PhoneBook) data.getDataFromPerm(Permissions.PHONEBOOK); //phoneBook of the App updates to main phone phoneBook, each time the user opens the App 
		database.CheckdeletedContacts(phoneBook);//delete chats corresponding to deleted contacts in the main phone phoneBook
		
	}
}