<<<<<<< HEAD
package FileIO;

public class FileIO {

=======
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

import javax.swing.JOptionPane;
//Peter i made a change
public class FileIO {
	Formatter file;
	private UserInfo userObj = new UserInfo();
	
	public void setNewUser(UserInfo userObj){
		this.userObj = userObj;
		newUserCheck();
	}
	
	public void addPassword(){
		file.format("$$$PASSWORD$$$%s", userObj.getPassword());////////////////why is this not working////////////////////////////////////////
	}
	
	public void newUserCheck(){
		File userFile = new File(userObj.getUser() + ".java");
		
		if(userFile.exists()){
			JOptionPane.showMessageDialog(null, userFile.getName() + " Exists");
		}else{
			JOptionPane.showMessageDialog(null, userObj.getUser() + " is not a known username.");
			JOptionPane.showMessageDialog(null, "Creating User: " + userObj.getUser());
			String temppass = JOptionPane.showInputDialog(null, "Please re-enter password");
			while(!userObj.getPassword().equals(temppass)){
				userObj.setPassword(temppass);
				temppass = JOptionPane.showInputDialog(null, "Password doesn't match, please re-enter.");
			}
			try{
				file= new Formatter(userFile.getName());
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, userFile.getName() + " was created");
			addPassword();
		}
	}
>>>>>>> 092b6d39ea82e4906b7698cd621bb08e90eb297f
}
