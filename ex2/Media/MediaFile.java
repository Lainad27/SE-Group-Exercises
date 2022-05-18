package ex2.Media;

import java.util.concurrent.TimeUnit;

public class MediaFile {
	public enum FileType{
		VIDEO,
		AUDIO
	}
	
	private String name;
	private FileType fileType;
	private int mediaLength;
	
	public MediaFile (String name, FileType fileType, int mediaLength) {
		this.name = name;
		this.fileType = fileType;
		this.mediaLength = mediaLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public int getMediaLength() {
		return mediaLength;
	}
	
	public String getLengthFormatted() {
		String formattedSeconds = "" + mediaLength%60;
		if (mediaLength%60 < 10)
			formattedSeconds = "0" + formattedSeconds;
		return mediaLength/60 + ":" + formattedSeconds;
	}

	public void setMediaLength(int mediaLength) {
		this.mediaLength = mediaLength;
	}
	
	public String toString() {
		return this.name + " is now playing for " + this.getLengthFormatted();
	}
	
	public void play() {
		System.out.println(this.toString());
		try {
			TimeUnit.SECONDS.sleep(mediaLength);// TODO what is this
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}
	
}
