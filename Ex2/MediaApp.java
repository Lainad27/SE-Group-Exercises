package ex2;

import java.util.ArrayList;

import ex2.MediaFile.FileType;
import ex2.MobilePhone.AppPermissionsData;

public class MediaApp extends App {
	
	private MediaDatabase database;
	
	
	public String getAppName() {
		return "Media app";
	}

	public void printOptions() {
		System.out.println("1 - Add media file");
		System.out.println("2 - Play media file");
		System.out.println("3 - Play all media files");
		System.out.println("leave - leave.");	
	}

	public void handleCommand(String command) {
		switch (command) {
		case "1": // get the parameters from the user and add a new media with those parameters
			System.out.println("Please enter the name of the media you would like to add: ");
			String name = input.nextLine();
			System.out.println("Please enter the amount of seconds it is: ");
			int seconds = input.nextInt();
			System.out.println("Please enter the amount of minutes it is: ");
			int minutes = input.nextInt();
			input.nextLine();  // Consume newline left-over
			int totalLength = 60*minutes + seconds;
			System.out.println("Please enter the file type (video/audio): ");
			String fileTypeString = input.nextLine();
			if (fileTypeString.toLowerCase().compareTo("video") == 0)
				database.addMedia(name, FileType.VIDEO, totalLength);
			else if (fileTypeString.toLowerCase().compareTo("audio") == 0)
				database.addMedia(name, FileType.AUDIO, totalLength);
			else
				System.out.println("Invalid file type.");	
			break;
		case "2": // play media by name
			System.out.println("What's the name of the media you would like to play?");
			String mediaName = input.nextLine();
			database.playMediaByName(mediaName);
			break;
		case "3": // play all media exist
			database.playAllMedia();
			break;
		default:
			System.out.println("Invalid command. Please enter \"h\" to see command options.");
			break; 
		}
		
	}

	public void init(AppPermissionsData data) {
		return;		
	}
	

}
