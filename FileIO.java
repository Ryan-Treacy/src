import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class FileIO {
	private static UserInfo userObj = new UserInfo();
	private static File userFile; 
	private static File guestFile = new File("GUEST.java");
	
	
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
}