package java_programming;


public class NameUtility {

	public static String convertNameToInitials(String name) {

		if(name.length() < 1)
			return name;
		String[] words = name.split(" ");
		String initials = "";		
		for (int i = 0; i < words.length; i++) {
			initials += words[i].substring(0, 1) + ".";
		}
		return initials;

//		int firstSpace = name.indexOf(" ");
//		int lastSpace = name.lastIndexOf(" ");
//		String firstName = name.substring(0, firstSpace);
//		String middleName = name.substring(firstSpace, lastSpace);
//		String lastName = name.substring(lastSpace);
//		String initials = firstName + "." + middleName + "." + lastName + ".";
//		return initials;
	}

}
