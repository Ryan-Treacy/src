import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Search {
	// private String temp = "find @user and #topic test string.";

	public static String searchForTagUser(String src){
		String user="";
		Matcher matcher = Pattern.compile("@(\\w+)").matcher(src);
		while (matcher.find()) {
			user += matcher.group(1) + "\n";
		}
		return user;
	}
	
	public static String searchForTopic(String src){
		String topic="";
		Matcher matcher = Pattern.compile("#(\\w+)").matcher(src);
		while (matcher.find()) {
			topic += matcher.group(1) + "\n";
		}
		return topic;
	}
}
