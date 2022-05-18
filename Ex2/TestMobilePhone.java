package ex2;

import java.util.Date;

import ex2.Media.MediaFile.FileType;
import ex2.SMS.Chat;

public class TestMobilePhone {

	public static void main(String[] args) {
		MobilePhone myPhone = new MobilePhone();
		//phonebook tests:
		myPhone.phoneBook.addContact("Daniel", "0500000000");
		myPhone.phoneBook.addContact("Shon", "0500000001");
		myPhone.phoneBook.sortPhonebookByName();
		myPhone.phoneBook.printAllContacts();
		//sms app tests:
		Chat chat = new Chat(myPhone.phoneBook.searchContact("Daniel"));
		myPhone.smsApp.database.addChat(chat);
		chat.addMessage("Hey Daniel");
		myPhone.smsApp.database.showAllChats();
		//media app tests:
		myPhone.mediaApp.database.addMedia("my video", FileType.VIDEO, 1);
		myPhone.mediaApp.database.addMedia("my audio", FileType.AUDIO, 2);
		myPhone.mediaApp.database.playAllMedia();
		//calendar app tests:
		Date date = new Date(2022,5,2,12,30);
		myPhone.calenderApp.calendar.addEvent(date, 60000*60, myPhone.phoneBook.searchContact("Daniel"));
		Date date2 = new Date(2022,5,2,12,45);
		myPhone.calenderApp.calendar.addEvent(date2, 60000*60, myPhone.phoneBook.searchContact("Shon"));
		myPhone.calenderApp.calendar.printAllEvents();
		
		myPhone.calenderApp.calendar.deleteOverlaps();
		myPhone.calenderApp.calendar.printAllEvents();
		
		myPhone.turnOn(); //this is how the phone actually operates
	}

}
