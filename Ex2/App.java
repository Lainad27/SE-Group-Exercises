package ex2;

import java.util.ArrayList;
import java.util.Scanner;

import ex1.Contact;

public abstract class App {
	static Scanner input = new Scanner(System.in);
	
	public abstract void printOptions();
	public abstract void handleCommand(String command);
	public abstract String getAppName();
	public abstract void phoneBookChanged(ArrayList<Contact> newPhoneBook);
	
	public void run(ArrayList<Contact> newPhoneBook) {
		
		phoneBookChanged(newPhoneBook);
		
		boolean programEnded = false;
		while (!programEnded) {
			System.out.println("Entered " + getAppName() + "! What command would you like to execute? \n\"help\" to see options, \"leave\" to leave");
			System.out.print("> ");
			String command = input.next();
			
			switch (command) {
			case "help":
			case "h":
				printOptions();
				break;
			case "leave":
			case "LEAVE":
				programEnded=true;
				break;
			default:
				handleCommand(command);
				break;
			}
		}
	}
}
