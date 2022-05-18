package ex2.Media;

import java.util.ArrayList;

import ex2.App;
import ex2.MobilePhone;
import ex2.Media.MediaFile.FileType;
import ex2.MobilePhone.AppPermissionsData;

public class MediaApp extends App {
	
	MediaDatabase database = new MediaDatabase();
	
	
	public String getAppName() {
		return "Media app";
	}

	public String getOptions() {
		return "1 - Add media file\n"
		+ "2 - Play media file\n"
		+ "3 - Play all media files";
	}

	public void handleCommand(String command) {
		switch (command) {
		case "1":
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
			else if (fileTypeString.toLowerCase().compareTo("audio")==0)
				database.addMedia(name, FileType.AUDIO, totalLength);
			else
				System.out.println("Invalid file type.");	
			break;
		case "2":
			System.out.println("What's the name of the media you would like to play?");
			String mediaName = input.nextLine();
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
