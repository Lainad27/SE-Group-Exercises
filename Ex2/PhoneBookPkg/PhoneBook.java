package ex2.PhoneBookPkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class PhoneBook {
	
	private ArrayList<Contact> phoneBook = new ArrayList<Contact>();
	
	public boolean isEmpty() {
		return phoneBook.isEmpty();
	}
	
	public void addContact(String name, String phone) { //1
		if (phone.length() != 10 || !phone.startsWith("05") ) {
			System.out.println("Invalid number.");
			return;
		}
		if (searchContact(name) != null ) {
			System.out.println("Contact already exists.");
			return;
		}
		Contact e = new Contact(name,phone);
		phoneBook.add(e);
		System.out.println("Contact added.");
	}
	public Contact searchContact(String name) {//2 only first occurrence
		for(Contact cnt: phoneBook) {
			if(cnt.getName().compareTo(name) == 0 ) {
				return cnt;
			}
		}
		return null;
	}
	
	public void deleteContact (String name) {//2
		Contact e = searchContact(name);
		if (e==null) {
			System.out.println("Invalid name.");
			return;
		}
		phoneBook.remove(e);
		System.out.println("Contact deleted.");
    }
	
	public void printAllContacts() {  //3
		for (Contact contact : phoneBook) {
			contact.print();
		}
	}
	public void printAllContactNames() { 
		for (Contact contact : phoneBook) {
			System.out.println(contact.getName());
		}
	}
	public List<Contact> contactLookupByName(String name) {   //(4 all occurrences
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
	
	
	public void sortPhonebookByName () {   //(5 TODO use sort method as specified
		
		Collections.sort(phoneBook, new Comparator<Contact>()
        {
            public int compare(Contact contact1, Contact contact2)
            {
                return contact1.getName().compareTo(contact2.getName());
            }        
        }
		);
	
		}
	public void sortPhonebookByNumber () {   //(6
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
	public void removeDuplicates () { //7
		List<Contact> list = new ArrayList<Contact>(phoneBook);
		String name;
		boolean smtngDeleted = false;
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<list.size();j++) {
				if(i!=j && list.get(i).getName().compareTo(list.get(j).getName())==0 && list.get(i).getPhoneNumber().compareTo(list.get(j).getPhoneNumber())==0 ) {
					name = list.get(i).getName();
					deleteContact(name);
					smtngDeleted = true;
					list.remove(i);
				}
			}
			
		
		}
		if (smtngDeleted)
			System.out.println("duplicates removed from phonebook.");
	}

	public void reversePhoneBook() {   //(8 we will solve recursively
		// base case: the list is empty, or only one element is left
        if (phoneBook == null || phoneBook.size() <= 1) {
            return;
        }
	
        // remove the first element
        Contact value = phoneBook.remove(0);
 
        // recur for remaining items
        reversePhoneBook();
 
        // insert the top element back after recurse for remaining items
        phoneBook.add(value);
	}
	
	public void savePhonebook(String fileName) {//    (9)
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
	
	public void loadPhonebook(String loadFileName) {
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
