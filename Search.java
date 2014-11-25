import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Search {
	
	public static void searchForTag(String entry){
		
		// Uses regular expressions to check if user typed any tag words.
		Matcher matcherUser = Pattern.compile("@(\\S+)").matcher(entry);
		Matcher matcherTopic = Pattern.compile("(#\\w+)").matcher(entry);
		
		// Checks matched @user result to see if it may be an email address.  If it is, it is rejected. 
		//		If matches are not rejected, the user entry is added to the appropriate files.
		while (matcherUser.find()) {
			String user = matcherUser.group(1);
			if(!user.contains(".com")){
				FileIO.tagWrite(user, entry);
			}
		}
		
		// Writes tagged #topic to file.
		while (matcherTopic.find()) {
			String topic = matcherTopic.group(1);
			FileIO.tagWrite(topic, entry);			
		}
	}
}
