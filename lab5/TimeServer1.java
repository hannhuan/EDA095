package lab5;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class TimeServer1 {

	public TimeServer1() {
		while (true) {
			System.out.println("Write your command: ");
			Scanner scan = new Scanner(System.in);
			String command = scan.nextLine();
			Date date = new Date();
			timeServer1(date, command);
		}
	}

	public void timeServer1(Date date, String command) {

		DateFormat df = null;
		switch (command) {
		case "date":
			df = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
			break;
		case "time":
			df = DateFormat.getTimeInstance(DateFormat.FULL, Locale.ENGLISH);
			break;
		default:
			System.out.println("Check your command.");
			return;
		}
		System.out.println(df.format(date));

	}

	public static void main(String[] args) {
		TimeServer1 ts = new TimeServer1();
	}

}
