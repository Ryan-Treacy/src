import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class FileIO {
	
	private static UserInfo userObj = new UserInfo();
	private static File userFile; 
	private static File guestFile;
	private static File tagFile;
	private static File subFile;
	private static File postFile;
	
	//This checks to see if a user subscriber file exists.  If it doesn't, it creates it and adds a name to the list.  If it does exist,
	//		it will check the list to see if the name is on the list, if it is it lets the user know they are allready subcribes, if not,
	//		the user is added to the list.
	public static void addToSubs(String str){
		if(str.startsWith("@")){
			str = str.substring(1);
		}
		subFile = new File(str + "Subs.UMW");
		boolean exists = false;
		if(subFile.exists()){
			try {
				Scanner input = new Scanner(subFile);
				while(input.hasNextLine()){
					if(input.nextLine().contentEquals(userObj.getUser())){
						exists = true;
					}
				}
				input.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(!exists){
			updateFile(userObj.getUser() + "\n", subFile);
			JOptionPane.showMessageDialog(null, "You are now subscribed to " + str);
		}else{
			JOptionPane.showMessageDialog(null, "You are already subscribed.");
		}
	}
	
	//  This adds the posted text from a user to all of that users subscribers files.
	public static void postToSubs(String str){
		if(!userObj.getUser().contentEquals("GUEST")){
			subFile = new File(userObj.getUser() + "Subs.UMW");
			if(subFile.exists()){
				try {
					Scanner input = new Scanner(subFile);
					while(input.hasNextLine()){
						String tempFileName = input.nextLine();	
						postFile = new File(tempFileName + ".UMW");
						if(postFile.exists()){
							updateFile(userObj.getUser() + ": " + str + "\n", postFile);
						}
					}
					input.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// This creates a guest file with default greeting.  This only happens the first time the program is run.
	public static void createGuestFile(){
		String welcome = "Welcome To UMW CompSci POST IT!\nYou are currently logged in on the GUEST account.\nYou can view public messages on the GUEST page from the guest account.\nCreate a personal account or sign in entering your username and password above.\n\n";
		guestFile = new File("GUEST.UMW");
		if(!guestFile.exists()){
			updateFile(userObj.getPassword() + "\n", guestFile);
			updateFile(welcome + "\n", guestFile);
		}
	}
	
	
	// This searches to see if a user file or topic file exists when the user searches for it.
	public static boolean tagSearch(String str){
			if(str.startsWith("@")){
				str = str.substring(1);
			}
			setTagFile(new File(str + ".UMW"));
			if(getTagFile().exists()){
				return true;
			}else{
				JOptionPane.showMessageDialog(null, "User/Topic does not exist.");
				return false;
			}
	}
	
	
	// This creates a topic file or adds to the existing file when a user tags one #topic.  It also writes to user files if they are tagged @user
	public static void tagWrite(String tag, String entry){
		setTagFile(new File(tag + ".UMW"));
		if(tag.startsWith("#")){
			updateFile(userObj.getUser() + ": " + entry + "\n", getTagFile());
		}else{
			if(getTagFile().exists()){
				updateFile(userObj.getUser() + ": " + entry + "\n", getTagFile());
			}
		}
	}	
	
	// adds the password as the first line in a user file when a new user is created.
	public void addPassword(){
		try {
			PrintWriter output = new PrintWriter(getFile());
			output.println(userObj.getPassword());
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	// This prompts the user for information to store as the first lines in their profile when a new user is created.
	public void createProfile(){
		String temp = "";
		int result;
		result = JOptionPane.showConfirmDialog(null, "Would you like to create a profile about yourself?",null, JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION){
			temp += ("Name: " + JOptionPane.showInputDialog(null, "Enter your name.") + "\n");
			temp += ("Class: " + JOptionPane.showInputDialog(null, "What UMW class are you?") + "\n");
			temp += ("Phone Number: " + JOptionPane.showInputDialog(null, "What is your phone number?") + "\n\n" + "Posts:\n");
			updateFile(temp,getFile());
		} 
	}
	
	// This validates the users password.
	public boolean checkPassword(){
		boolean temp = false;
		try {
			Scanner input = new Scanner(getFile());
			if(input.nextLine().equals(userObj.getPassword())){
				input.close();
				temp = true;
			}else{
				JOptionPane.showMessageDialog(null, "Password doesn't match.");
				input.close();
				temp = false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	// This checks to user if the user is new or returning.  If new user, calls functions to create new files. if returning user,
	//		calls the validate password functions.
	public boolean newUserCheck(){
		boolean temp;
		setFile(new File(userObj.getUser() + ".UMW"));
		if(getFile().exists()){
			if(checkPassword()){
				temp = true;
			}else{
				temp = false;
			}
		}else{
			JOptionPane.showMessageDialog(null, userObj.getUser() + " is not a known username.");
			JOptionPane.showMessageDialog(null, "Creating User: " + userObj.getUser());
			String temppass = JOptionPane.showInputDialog(null, "Please re-enter password");
			while(!userObj.getPassword().equals(temppass)){
				userObj.setPassword(temppass);
				temppass = JOptionPane.showInputDialog(null, "Password doesn't match, please re-enter.");
			}
			JOptionPane.showMessageDialog(null, "User " + userObj.getUser() + " was created");
			addPassword();	
			createProfile();
			temp = true;
		}
		return temp;
	}
	
	// This is used to update files with new posts from user. 
	public static void updateFile(String str, File file){
		try {
			FileWriter output = new FileWriter(file, true);
			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Getters and Setters. 
	
	public void setNewUser(UserInfo userObj){
		FileIO.userObj = userObj;
	}
	
	public static File getFile() {
		return userFile;
	}

	public static void setFile(File userFile) {
		FileIO.userFile = userFile;
	}

	public static File getGuestFile() {
		return guestFile;
	}

	public static void setGuestFile(File guestFile) {
		FileIO.guestFile = guestFile;
	}

	public static File getTagFile() {
		return tagFile;
	}

	public static void setTagFile(File tagFile) {
		FileIO.tagFile = tagFile;
	}

	public static File getSubFile() {
		return subFile;
	}

	public static void setSubFile(File subFile) {
		FileIO.subFile = subFile;
	}
}