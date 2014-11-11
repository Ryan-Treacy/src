import java.io.File;

import javax.swing.JOptionPane;


public class FileIO {
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
			JOptionPane.showMessageDialog(null, userFile.getName() + " needs to be created");
		}
		
	}
	
}