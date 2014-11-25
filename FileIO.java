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
	
	public static void addToSubs(String str){
		subFile = new File(str + "Subs.java");
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
		}else{
			JOptionPane.showMessageDialog(null, "You are already subscribed.");
		}
	}
	
	public static void postToSubs(String str){
		if(!userObj.getUser().contentEquals("GUEST")){
			subFile = new File(userObj.getUser() + "Subs.java");
			if(subFile.exists()){
				try {
					Scanner input = new Scanner(subFile);
					while(input.hasNextLine()){
						String tempFileName = input.nextLine();	
						postFile = new File(tempFileName + ".java");
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
	
	public static void createGuestFile(){
		String welcome = "Welcome To UMW CompSci POST IT!\nYou are currently logged in on the GUEST account.\nYou can view public messages on the GUEST page from the guest account.\nCreate a personal account or sign in entering your username and password above.\n\n";
		guestFile = new File("GUEST.java");
		if(!guestFile.exists()){
			updateFile(userObj.getPassword() + "\n", guestFile);
			updateFile(welcome + "\n", guestFile);
		}
	}
	
	public static boolean tagSearch(String str){
			if(str.startsWith("@")){
				str = str.substring(1);
			}
			setTagFile(new File(str + ".java"));
			if(getTagFile().exists()){
				return true;
			}else{
				JOptionPane.showMessageDialog(null, "User/Topic does not exist.");
				return false;
			}
	}
	
	
	
	public static void tagWrite(String tag, String entry){
		setTagFile(new File(tag + ".java"));
		if(tag.startsWith("#")){
			updateFile(userObj.getUser() + ": " + entry + "\n", getTagFile());
		}else{
			if(getTagFile().exists()){
				updateFile(userObj.getUser() + ": " + entry + "\n", getTagFile());
			}
		}
	}	
	
	public void setNewUser(UserInfo userObj){
		FileIO.userObj = userObj;
	}
	
	public void addPassword(){
		try {
			PrintWriter output = new PrintWriter(getFile());
			output.println(userObj.getPassword());
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
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
	
	public boolean newUserCheck(){
		boolean temp;
		setFile(new File(userObj.getUser() + ".java"));
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
	
	public static void updateFile(String str, File file){
		try {
			FileWriter output = new FileWriter(file, true);
			output.write(str);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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