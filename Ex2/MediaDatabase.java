package ex2;

import java.util.*;

import ex2.MediaFile.FileType;

public class MediaDatabase {
	
	private HashSet<MediaFile> data;

	public MediaDatabase() {
		this.data = new HashSet<MediaFile>();
	}
	
	public void addMedia (String name, FileType fileType, int mediaLength) {
		MediaFile newFile = new MediaFile(name, fileType, mediaLength);
		data.add(newFile);
		System.out.println("Media file added.");
	}
	
	public MediaFile searchMedia (String name) {
		Iterator<MediaFile> it= data.iterator();
		while (it.hasNext()) {
			MediaFile resFile = it.next();
			if (resFile.getName() == name)
				return resFile;
		}
		return null;
	}
	
	public void playMediaByName (String name) {
		MediaFile fileToPlay = searchMedia(name);
		if (fileToPlay==null) {
			System.out.println("There is no media file with that name.");
			return;
		}
		fileToPlay.play();
	}
	
	public void playAllMedia() {
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
