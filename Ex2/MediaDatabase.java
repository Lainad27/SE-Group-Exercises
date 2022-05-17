package ex2;

import java.util.*;

import ex2.MediaFile.FileType;

public class MediaDatabase {
	
	private HashSet<MediaFile> data;

	public MediaDatabase() {
		this.data = new HashSet<MediaFile>();
	}
	
	public void addMedia (String name, FileType fileType, int mediaLength) { // add a new Media 
		MediaFile newFile = new MediaFile(name, fileType, mediaLength);
		data.add(newFile);
		System.out.println("Media file added.");
	}
	
	public MediaFile searchMedia (String name) { // search contact by name, return the first Media with that name
		Iterator<MediaFile> it= data.iterator();
		while (it.hasNext()) {
			MediaFile resFile = it.next();
			if (resFile.getName().compareTo(name) == 0)
				return resFile;
		}
		return null;
	}
	
	public void playMediaByName (String name) { // print Media by name, the function will print the name and the length of the media
		MediaFile fileToPlay = searchMedia(name);
		if (fileToPlay==null) {
			System.out.println("There is no media file with that name.");
			return;
		}
		fileToPlay.play();
	}
	
	public void playAllMedia() { // print all media exist, the function will print the name and the length for each media.
		if (data.isEmpty()) {
			System.out.println("There are currently no media files.");
			return;
		}
		Iterator<MediaFile> it= data.iterator();
		while (it.hasNext()) {
			MediaFile resFile = it.next();
			resFile.play();
		}
	}
	
	
	

}
