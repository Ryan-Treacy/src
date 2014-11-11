import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

import javax.swing.JOptionPane;


public class FileIO {
	Formatter formatter;
	private UserInfo userObj = new UserInfo();
	
	public void setNewUser(UserInfo userObj){
		this.userObj = userObj;
		newUserCheck();
	}
	
	public void newUserCheck(){
		File userFile = new File(userObj.getUser() + ".txt");
		
		if(userFile.exists()){
			JOptionPane.showMessageDialog(null, userFile.getName() + " Exists");
		}else{
			JOptionPane.showMessageDialog(null, userObj.getUser() + " is not a known user.");
			JOptionPane.showMessageDialog(null, "Creating User: " + userObj.getUser());
			String temppass = JOptionPane.showInputDialog(null, "Please re-enter password");
			if(temppass.equals(userObj.getPassword())){
				try{
					formatter = new Formatter(userFile.getName());
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, userFile.getName() + " was created");
			}else{
				while(!userObj.getPassword().equals(temppass)){
					userObj.setPassword(temppass);
					temppass = JOptionPane.showInputDialog(null, "Password don't match, please re-enter password");
				}
			}
		}
	}
}