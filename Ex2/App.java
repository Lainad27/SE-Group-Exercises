package ex2;

import java.util.Scanner;

import ex2.MobilePhone.AppPermissionsData;

public abstract class App {
	public static Scanner input = new Scanner(System.in);
	public abstract String getOptions();
	public void printOptions() {
		System.out.println(getOptions());
		System.out.println("leave - leave the app");
	}
	public abstract void handleCommand(String command);
	public abstract String getAppName();
	public abstract void init(AppPermissionsData data);
	
	public void run(AppPermissionsData data) {
		
		init(data);
		
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
