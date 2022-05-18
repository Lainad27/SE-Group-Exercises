package ex2;

import java.util.Scanner;

import ex2.MobilePhone.AppPermissionsData;

public abstract class App { //all apps will extend this class
	public static Scanner input = new Scanner(System.in); //all apps need a scanner
	public abstract String getOptions(); // this is a string with all the app options
	public void printOptions() { // the final option string will also need to include the leave command
		System.out.println(getOptions());
		System.out.println("leave - leave the app");
	}
	public abstract void handleCommand(String command); // apps needs to handle commands
	public abstract String getAppName(); 
	public abstract void init(AppPermissionsData data); //this function is called when the app is opened
	
	public void run(AppPermissionsData data) { //this is how we run an app
		
		init(data); // run function on input data - for example the phonebook maybe updated and we need to modify data accordingly
		
		System.out.println("Entered " + getAppName() + "!");
		boolean programEnded = false;
		while (!programEnded) {
			System.out.println("What command would you like to execute? \n\"help\" to see options, \"leave\" to leave");
			System.out.print(">> ");
			String command = input.nextLine().toLowerCase();
			
			switch (command) {
			case "help":
			case "h":
				printOptions();
				break;
			case "leave":
			case "l":
				programEnded=true;
				break;
			default:
				handleCommand(command);
				break;
			}
		}
	}
}
