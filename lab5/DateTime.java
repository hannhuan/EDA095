package lab5;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DateTime {

	public static void main(String[] args) {
		Date date = new Date();
		ArrayList<String> nbr = new ArrayList<String>();
		nbr.add("German");
		nbr.add("English");
		nbr.add("French");
		nbr.add("Chinese");
		nbr.add("Spanish");
		for (int i = 0; i < nbr.size(); i++) {
			String region = nbr.get(i);
			DateFormat df = null;
			switch (region) {
			case "German":
				df = DateFormat.getDateInstance(DateFormat.FULL, Locale.GERMAN);
				break;
			case "English":
				df = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
				break;
			case "French":
				df = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);
				break;
			case "Chinese":
				df = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINESE);
				break;
			default:
				System.out.println(region + " unkown");
				return;
			}
			String dateString = df.format(date);
			System.out.println(dateString);
		}
	}
}
