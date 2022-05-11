package ex2;

import java.util.Scanner;

import ex2.MobilePhone.AppPermissionsData;

public abstract class App { //All apps will inherit this class
	static Scanner input = new Scanner(System.in); //The app needs a scanner for input
	
	public abstract void printOptions(); //prints all command options
	public abstract void handleCommand(String command); //Given a command, the app decides what to do.
	public abstract String getAppName(); // Return's the name of the app.
	public abstract void init(AppPermissionsData data); // This function will be called right after the app is started, so the app gets the latest data (like the phonebook)
	
	public void run(AppPermissionsData data) { //This function details how the app runs (taking commands)
		
		init(data); // use latest data from phone
		
		System.out.println("Entered " + getAppName() + "!");
		boolean programEnded = false;
		while (!programEnded) {
			System.out.println("What command would you like to execute? \n\"help\" to see options, \"leave\" to leave");
			System.out.print(">> ");
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
