import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Search {
	
	public static void searchForTag(String entry){
		Matcher matcherUser = Pattern.compile("@(\\S+)").matcher(entry);
		Matcher matcherTopic = Pattern.compile("(#\\w+)").matcher(entry);
		while (matcherUser.find()) {
			String user = matcherUser.group(1);
			if(!user.contains(".com")){
				FileIO.tagWrite(user, entry);
			}
		}
		while (matcherTopic.find()) {
			String topic = matcherTopic.group(1);
			FileIO.tagWrite(topic, entry);			
		}
	}
}
