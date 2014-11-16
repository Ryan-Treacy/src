import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Search {
	public static String searchForTagUser(String src){
		String user="";
		Matcher matcher = Pattern.compile("@(\\S+)").matcher(src);
		while (matcher.find()) {
			String temp = matcher.group(1);
			if(!temp.contains(".com")){
				user += temp + "\n";
			}
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
