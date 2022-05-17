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
		case "1":
			System.out.println("Please enter the name of the media you would like to add: ");
			String name = input.next();
			System.out.println("Please enter the amount of seconds it is: ");
			int seconds = Integer.parseInt(input.next());
			System.out.println("Please enter the amount of minutes it is: ");
			int minutes = Integer.parseInt(input.next());
			System.out.println("Please enter the file type (video/audio): ");
			int totalLength = 60*minutes + seconds;
			String fileTypeString = input.next();
			if (fileTypeString.toLowerCase() == "video")
				database.addMedia(name, FileType.VIDEO, totalLength);
			else if (fileTypeString.toLowerCase() == "audio")
				database.addMedia(name, FileType.AUDIO, totalLength);
			else
				System.out.println("Invalid file type.");	
			break;
		case "2":
			System.out.println("What's the name of the media you would like to play?");
			String mediaName = input.next();
			database.playMediaByName(mediaName);
			break;
		case "3":
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
