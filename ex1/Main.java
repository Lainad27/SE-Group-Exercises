package ex1;

import java.util.*;
import java.io.*;

public class Main {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		LinkedList<Contact> phoneBook = new LinkedList<Contact>();
		boolean programEnded = false;
		
		while (!programEnded) {
			System.out.println("What command would you like to execute? \n\"help\" to see options");
			System.out.print("> ");
			String command = input.next();
			switch (command) {
			case "help":
			case "h":
				System.out.println("1 - add contact to phonebook");
				System.out.println("2 - delete contact from phonebook by name");
				System.out.println("3 - print all phonebook contacts to screen");
				System.out.println("4 - lookup phonebook contact by name");
				System.out.println("5 - sort phonebook by contact name");
				System.out.println("6 - sort phonebook by phone number");
				System.out.println("7 - remove duplicates in phonebook");
				System.out.println("8 - flip phonebook order");
				System.out.println("9 - save phonebook to file");
				System.out.println("10 - load additional contacts to phonebook from file");
				System.out.println("11 - exit the program");
				break;
			case "1":
				System.out.print("what's the name of the contact you would like to create? ");
				String newContactName = input.next();
				System.out.print("And whats their phonenumber? ");
				String newContactNumber = (input.next());
				addContact(newContactName, newContactNumber, phoneBook);
				break;
			case "2":
				System.out.print("what's the name of the contact you would like to delete? ");
				String name = input.next();
				deleteContact(name, phoneBook);
				break;
			case "3":
				printAllContacts(phoneBook);
				break;
			case "4":
				System.out.print("what's the name of the contact you would like to lookup? ");
				String cntName = input.next();
				List<Contact> result = contactLookupByName(phoneBook,cntName);
				if (result.size()!=0)
					System.out.println(contactLookupByName(phoneBook,cntName));
				break;
			case "5":
				sortPhonebookByName(phoneBook);
				System.out.println("Phonebook sorted by name.");
				break;
			case "6":
				sortPhonebookByNumber(phoneBook);
				System.out.println("Phonebook sorted by number.");
				break;
			case "7":
				removeDuplicates(phoneBook);
				break;
			case "8":
				reversePhoneBook(phoneBook);
				System.out.println("Phonebook reversed.");
				break;
			case "9":
				System.out.print("Please insert the name of the text file in which you want to save the PhoneBook: ");
				String fileName = input.next();
				savePhonebook(phoneBook, fileName);
				System.out.println("Phonebook saved in " + fileName + ".txt");
				break;
			case "10":
				System.out.print("insert the name of the text file from which you want to load contacts: ");
				String loadFileName = input.next();
				loadPhonebook(phoneBook, loadFileName);
				System.out.println("additional contacts loaded from " + loadFileName + ".txt");
				break;
			case "11":
				System.out.println("Exiting program . . .");
				programEnded = true;
				break;
			default:
				System.out.println("Invalid command. Please enter \"h\" to see command options.");
				break;
			}
		}
	}
	public static void addContact(String name, String phone, LinkedList<Contact> phoneBook) { //1
		if (phone.length() != 10 || !phone.startsWith("05") ) {
			System.out.println("Invalid number.");
			return;
		}
		Contact e = new Contact(name,phone);
		phoneBook.add(e);
		System.out.println("Contact added.");
	}
	public static Contact searchcontact(String name, LinkedList<Contact> phoneBook) {//2
		for(Contact cnt: phoneBook) {
			if(cnt.getName().compareTo(name) == 0 ) {
				return cnt;
			}
		}
		return null;
	}
	
	public static void deleteContact (String name, LinkedList<Contact> phoneBook) {//2
		Contact e = searchcontact(name, phoneBook);
		if (e==null) {
			System.out.println("Invalid name.");
			return;
		}
		phoneBook.remove(e);
		System.out.println("Contact deleted.");
    }
	
	public static void printAllContacts(List<Contact> phoneBook) {  //3
		for (Contact contact : phoneBook) {
			contact.print();
		}
	}
	public static List<Contact> contactLookupByName(List<Contact> phoneBook, String name) {   //(4
		ArrayList<Contact> appear = new ArrayList<Contact>();
		//put each appearance in ArrayList 
	    for (Contact contact : phoneBook) {
			if (name.equals(contact.getName()))
				appear.add(contact);
	    }
	    //if this contact does not exist in the phoneBook
	    if (appear.size()==0)
	    	System.out.println("This contact does not exist");
	    
	    //returns all appearances of the contact
		return appear;
	}
	
	
	public static void sortPhonebookByName (List<Contact> phoneBook) {   //(5
		ArrayList<Contact> arrList = new ArrayList<Contact>();
		Iterator<Contact> it1= phoneBook.iterator();

		//transfer LinkedList to ArrayList
		while(it1.hasNext()) {
	        Contact curr = phoneBook.remove(0);
	        arrList.add(curr);
		}
		
		//bubble sort
	    for (int i=0; i<arrList.size(); i++) {
		    for (int j=0; j<arrList.size() - i -1; j++) {
		    	if(arrList.get(j).getName().compareTo(arrList.get(j+1).getName())>0) {
		    		Contact temp = arrList.get(j);
		    		arrList.set(j, arrList.get(j+1));
		    		arrList.set(j+1 ,temp);
		    	}
		    }
	    }
	    
	    // update Contact LinkedList
	    for (int i=0; i<arrList.size(); i++) {
	    	phoneBook.add(arrList.get(i));
	    }
	
		}
	public static void sortPhonebookByNumber (List<Contact> phoneBook) {   //(6
		ArrayList<Contact> arrList = new ArrayList<Contact>();
		Iterator<Contact> it1= phoneBook.iterator();

		//transfer LinkedList to ArrayList
		while(it1.hasNext()) {
	        Contact curr = phoneBook.remove(0);
	        arrList.add(curr);
		}
		
		//bubble sort by phone number
	    for (int i=0; i<arrList.size(); i++) {
		    for (int j=0; j<arrList.size() - i -1; j++) {
		    	if(Long.parseLong(arrList.get(j).getPhoneNumber()) > Long.parseLong(arrList.get(j+1).getPhoneNumber())) {
		    		Contact temp = arrList.get(j);
		    		arrList.set(j, arrList.get(j+1));
		    		arrList.set(j+1 ,temp);
		    	}
		    }
	    }
	    
	    // update Contact LinkedList
	    for (int i=0; i<arrList.size(); i++) {
	    	phoneBook.add(arrList.get(i));
	    }
	
	}
	public static void removeDuplicates (LinkedList<Contact> phoneBook) { //7
		List<Contact> list = new ArrayList<Contact>(phoneBook);
		String name;
		boolean smtngDeleted = false;
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<list.size();j++) {
				if(i!=j && list.get(i).getName().compareTo(list.get(j).getName())==0 && list.get(i).getPhoneNumber().compareTo(list.get(j).getPhoneNumber())==0 ) {
					name = list.get(i).getName();
					deleteContact(name,phoneBook);
					smtngDeleted = true;
					list.remove(i);
				}
			}
			
		
		}
		if (smtngDeleted)
			System.out.println("duplicates removed from phonebook.");
	}

	public static void reversePhoneBook(List<Contact> phoneBook) {   //(8 we will solve recursively
		// base case: the list is empty, or only one element is left
        if (phoneBook == null || phoneBook.size() <= 1) {
            return;
        }
	
        // remove the first element
        Contact value = phoneBook.remove(0);
 
        // recur for remaining items
        reversePhoneBook(phoneBook);
 
        // insert the top element back after recurse for remaining items
        phoneBook.add(value);
	}
	
	public static void savePhonebook(List<Contact> phoneBook, String fileName) {//    (9)
		//get the filename from the user.
		
	    try {
		  File file = new File(fileName + ".txt"); 
		     if (file.createNewFile()) { // check if the file already exists
		       FileWriter fileWrite = new FileWriter(fileName + ".txt");
		       for (Contact contact : phoneBook) { //write each contact
					fileWrite.write(contact.toString() + "\n");					
			   }
		       fileWrite.close();
		     } else {
		       System.out.println("File already exists.");
		     }
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		  }
	}
	
	public static void loadPhonebook(List<Contact> phoneBook, String loadFileName) {
		  //get the filename from the user.    (10)
		
		
		try {
		      File file = new File(loadFileName +".txt");
		      if (file.exists()) {
		    	  
		      Scanner reader = new Scanner(file);
		      while (reader.hasNextLine()) {
		    	  
		        String contactString = reader.nextLine();
		        
		        String phoneNumberString = contactString.split(",")[0];
		        String contactName = contactString.split(",")[1];
		        Contact newContact = new Contact(contactName,phoneNumberString); 
		        phoneBook.add(newContact);
		      }
		      reader.close();
		      }
		      else {
		    	  System.out.println("This file does not exist.");
		      }
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		    }
	}
}
