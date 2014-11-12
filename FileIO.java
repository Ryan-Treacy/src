import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class FileIO {
	private UserInfo userObj = new UserInfo();
	private File userFile; 
	
	public void setNewUser(UserInfo userObj){
		this.userObj = userObj;
	}
	
	public void addPassword(){
		try {
			PrintWriter output = new PrintWriter(userFile);
			output.println(userObj.getPassword());
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public boolean checkPassword(){
		boolean temp = false;
		try {
			Scanner input = new Scanner(userFile);
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
	
	public void newUserCheck(){
		userFile = new File(userObj.getUser() + ".java");
		if(userFile.exists()){
			if(checkPassword()){
				JOptionPane.showMessageDialog(null, "Welcome " + userObj.getUser() + "!");
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
			JOptionPane.showMessageDialog(null, "Welcome " + userFile.getName() + "!");
		}
	}
}