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
			try{
				formatter = new Formatter(userFile.getName());
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, userFile.getName() + " was created");
		}
		
	}
	
}