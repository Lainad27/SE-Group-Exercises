package ex2.PhoneBookPkg;

import java.util.List;

import ex2.App;
import ex2.MobilePhone;
import ex2.MobilePhone.AppPermissionsData;
import ex2.MobilePhone.Permissions;

public class PhoneBookApp extends App {
	
	public PhoneBook phoneBook;
	
	
	public String getAppName() {
		return "PhoneBook app";
	}
	

	public String getOptions() {
		return "1 - add contact to phonebook\n"
		+ "2 - delete contact from phonebook by name\n"
		+ "3 - print all phonebook contacts to screen\n"
		+ "4 - lookup phonebook contact by name\n"
		+ "5 - sort phonebook by contact name\n"
		+ "6 - sort phonebook by phone number\n"
		+ "7 - remove duplicates in phonebook\n"
		+ "8 - flip phonebook order\n"
		+ "9 - save phonebook to file\n"
		+ "10 - load additional contacts to phonebook from file";
	}

	public void handleCommand(String command) {
		switch (command) {
		case "1":
			System.out.print("what's the name of the contact you would like to create? ");
			String newContactName = input.nextLine();
			System.out.print("And whats their phonenumber? ");
			String newContactNumber = (input.nextLine());
			phoneBook.addContact(newContactName, newContactNumber);
			break;
		case "2":
			System.out.print("what's the name of the contact you would like to delete? ");
			String name = input.nextLine();
			phoneBook.deleteContact(name);
			break;
		case "3":
			phoneBook.printAllContacts();
			break;
		case "4":
			System.out.print("what's the name of the contact you would like to lookup? ");
			String cntName = input.nextLine();
			List<Contact> result = phoneBook.contactLookupByName(cntName);
			if (result.size()!=0)
				System.out.println(phoneBook.contactLookupByName(cntName));
			break;
		case "5":
			phoneBook.sortPhonebookByName();
			System.out.println("Phonebook sorted by name.");
			break;
		case "6":
			phoneBook.sortPhonebookByNumber();
			System.out.println("Phonebook sorted by number.");
			break;
		case "7":
			phoneBook.removeDuplicates();
			break;
		case "8":
			phoneBook.reversePhoneBook(); 
			System.out.println("Phonebook reversed.");
			break;
		case "9":
			System.out.print("Please insert the name of the text file in which you want to save the PhoneBook: ");
			String fileName = input.nextLine();
			phoneBook.savePhonebook(fileName);
			System.out.println("Phonebook saved in " + fileName + ".txt");
			break;
		case "10":
			System.out.print("insert the name of the text file from which you want to load contacts: ");
			String loadFileName = input.nextLine();
			phoneBook.loadPhonebook(loadFileName);
			System.out.println("additional contacts loaded from " + loadFileName + ".txt");
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
