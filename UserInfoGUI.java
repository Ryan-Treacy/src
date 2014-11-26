import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class UserInfoGUI {
	
	// Components of the GUI
	private int charLimit = 140;
	private JFrame frmUmwCompsciPost;
	private JTextField userTF;
	private JPasswordField passwordTF;
	private UserInfo userObj = new UserInfo();
	private JTextField postitTF;
	private JTextArea profileTF;
	private JButton connectBTN;
	private JButton postitBTN;
	private JButton disconnectBTN;
	private JLabel passwordLBL;
	private JLabel userLBL;
	private JLabel lblEnterTextBelow;
	private JTextField topicTF;
	private JTextArea userProfileTA;
	private JButton topicBTN;
	private JLabel searchLBL;
	private JScrollPane profileSP;
	private JScrollPane userProfileSP;
	private JButton btnPrivatePost;
	private JButton subBTN;
	private JLabel subLBL;
	/**
	 * Launch the application.
	 */
	public void buildLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoGUI window = new UserInfoGUI();
					window.frmUmwCompsciPost.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInfoGUI() {
		initialize();
		guestSignon();
	}

	/**
	 *Initialize the contents of the frame. Also adds what actions to perform when clicked/key press to the
	 *buttons and text fields.
	 */
	private void initialize() {
		frmUmwCompsciPost = new JFrame();
		frmUmwCompsciPost.setTitle("UMW CompSci POST IT!");
		frmUmwCompsciPost.setResizable(false);
		frmUmwCompsciPost.setBounds(100, 100, 677, 529);
		frmUmwCompsciPost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmUmwCompsciPost.getContentPane().setLayout(null);
		
		userTF = new JTextField();
		userTF.setToolTipText("Enter exisiting or desired username");
		userTF.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		userTF.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(passwordTF.getText().isEmpty()){
					passwordTF.requestFocus(); 
				}else{
					validUserPass();
				}
			}
		});
		userTF.setBounds(512, 12, 114, 19);
		frmUmwCompsciPost.getContentPane().add(userTF);
		userTF.setColumns(10);
		
		passwordTF = new JPasswordField();
		passwordTF.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		passwordTF.setToolTipText("Enter existing or desired password");
		passwordTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(userTF.getText().isEmpty()){
					userTF.requestFocus();
				}else{
					validUserPass();
				}
			}
		});
		passwordTF.setBounds(512, 32, 114, 19);
		frmUmwCompsciPost.getContentPane().add(passwordTF);
		
		userLBL = new JLabel("User Name:");
		userLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		userLBL.setBounds(405, 14, 113, 15);
		frmUmwCompsciPost.getContentPane().add(userLBL);
		
		passwordLBL = new JLabel("Password:");
		passwordLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		passwordLBL.setBounds(405, 34, 101, 15);
		frmUmwCompsciPost.getContentPane().add(passwordLBL);
		
		connectBTN = new JButton("Connect");
		connectBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		connectBTN.setActionCommand("Connect");
		connectBTN.setToolTipText("Click to sign in/sign up");
		connectBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validUserPass();
				postitTF.requestFocus();
			}
		});
		connectBTN.setBounds(405, 63, 117, 25);
		frmUmwCompsciPost.getContentPane().add(connectBTN);
		
		disconnectBTN = new JButton("Disconnect");
		disconnectBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		disconnectBTN.setToolTipText("Disconnect from UMW  CompSci POST IT!");
		disconnectBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		disconnectBTN.setEnabled(false);
		disconnectBTN.setBounds(522, 63, 117, 25);
		frmUmwCompsciPost.getContentPane().add(disconnectBTN);
		
		postitTF = new JTextField();
		postitTF.setEditable(false);
		postitTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateProfile();
			}
		});
		postitTF.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		postitTF.setToolTipText("type here to stick a POST IT!");
		postitTF.setBounds(36, 28, 356, 25);
		postitTF.setDocument(new JTextFieldCharLimit(charLimit));
		frmUmwCompsciPost.getContentPane().add(postitTF);
		postitTF.setColumns(10);
		
		postitBTN = new JButton("POST IT!");
		postitBTN.setEnabled(false);
		postitBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		postitBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateProfile();
			}
		});
		postitBTN.setToolTipText("Click to POST IT!");
		postitBTN.setBounds(36, 63, 188, 25);
		frmUmwCompsciPost.getContentPane().add(postitBTN);
		
		lblEnterTextBelow = new JLabel("Currently logged in as:  " + userObj.getUser());
		lblEnterTextBelow.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblEnterTextBelow.setBounds(36, 14, 350, 15);
		frmUmwCompsciPost.getContentPane().add(lblEnterTextBelow);
		
		profileSP = new JScrollPane();
		profileSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		profileSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		profileSP.setBounds(36, 97, 603, 161);
		frmUmwCompsciPost.getContentPane().add(profileSP);
		
		profileTF = new JTextArea();
		profileTF.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		profileTF.setEditable(false);
		profileSP.setViewportView(profileTF);
		profileTF.setWrapStyleWord(true);
		profileTF.setLineWrap(true);
		
		userProfileSP = new JScrollPane();
		userProfileSP.setBounds(36, 340, 603, 149);
		frmUmwCompsciPost.getContentPane().add(userProfileSP);
		
		userProfileTA = new JTextArea();
		userProfileTA.setEditable(false);
		userProfileTA.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		userProfileTA.setWrapStyleWord(true);
		userProfileSP.setViewportView(userProfileTA);
		
		topicTF = new JTextField();
		topicTF.setEditable(false);
		topicTF.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		topicTF.setBounds(36, 303, 250, 25);
		frmUmwCompsciPost.getContentPane().add(topicTF);
		topicTF.setColumns(10);
		topicTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!topicTF.getText().contentEquals("")  && !topicTF.getText().contentEquals("GUEST")){ 
					if(FileIO.tagSearch(topicTF.getText())){
						loadTopic();
						if(!topicTF.getText().startsWith("#")){
							if(!userObj.getUser().contentEquals("GUEST")){
								subBTN.setEnabled(true);
							}
							subLBL.setText(topicTF.getText());
						}else{
							subBTN.setEnabled(false);
							subLBL.setText("");
						}
					}
				}
			}
		});	
		topicBTN = new JButton("Search");
		topicBTN.setEnabled(false);
		topicBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!topicTF.getText().contentEquals("")  && !topicTF.getText().contentEquals("GUEST")){
					if(FileIO.tagSearch(topicTF.getText())){
						loadTopic();
						if(!topicTF.getText().startsWith("#")){
							if(!userObj.getUser().contentEquals("GUEST")){
								subBTN.setEnabled(true);
							}
							subLBL.setText(topicTF.getText());
						}else{
							subBTN.setEnabled(false);
							subLBL.setText("");
						}
					}
				}
			}
		});
		topicBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		topicBTN.setBounds(298, 303, 91, 25);
		frmUmwCompsciPost.getContentPane().add(topicBTN);
		
		searchLBL = new JLabel("Search for a @User or #Topic.");
		searchLBL.setBounds(36, 270, 600, 21);
		frmUmwCompsciPost.getContentPane().add(searchLBL);
		searchLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		
		btnPrivatePost = new JButton("Private Post");
		btnPrivatePost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				privatePost();
			}
		});
		btnPrivatePost.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnPrivatePost.setBounds(253, 64, 133, 25);
		btnPrivatePost.setEnabled(false);
		frmUmwCompsciPost.getContentPane().add(btnPrivatePost);
		
		subBTN = new JButton("Subcribe to");
		subBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileIO.addToSubs(topicTF.getText());
			}
		});
		subBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		subBTN.setEnabled(false);
		subBTN.setBounds(401, 303, 117, 25);
		frmUmwCompsciPost.getContentPane().add(subBTN);
		
		subLBL = new JLabel("");
		subLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		subLBL.setBounds(522, 303, 117, 25);
		frmUmwCompsciPost.getContentPane().add(subLBL);
	}
	
	// Posts to only the people who are subscribed to the logged in user.
	public void privatePost(){
		String temp = postitTF.getText();
		if(!temp.equals("")){
			FileIO.updateFile(userObj.getUser() + ": " +  temp + "\n", FileIO.getFile());
			FileIO.postToSubs(temp);
			profileTF.append(userObj.getUser() + ": " + temp + "\n");
			profileTF.setCaretPosition(profileTF.getDocument().getLength());
			postitTF.setText("");
			postitTF.requestFocus();
			//search for user or topic tags 
			Search.searchForTag(temp);
		}else{
			postitTF.requestFocus();
		}
	}
	
	//  This updates the Profile text field part of the GUI when a user searches for something.
	public void updateProfile(){
		String temp = postitTF.getText();
		if(!temp.equals("")){
			FileIO.updateFile(userObj.getUser() + ": " +  temp + "\n", FileIO.getFile());
			if(!userObj.getUser().contentEquals("GUEST")){
				FileIO.updateFile(userObj.getUser() + ": " + temp + "\n", FileIO.getGuestFile());
			}
			FileIO.postToSubs(temp);
			profileTF.append(userObj.getUser() + ": " + temp + "\n");
			profileTF.setCaretPosition(profileTF.getDocument().getLength());
			postitTF.setText("");
			postitTF.requestFocus();
			//search for user or topic tags 
			Search.searchForTag(temp);
		}else{
			postitTF.requestFocus();
		}
	}
	
	//  This updates the Profile text field part of the GUI when a user searches for something.
	public void loadTopic(){
		userProfileTA.setText("");
		try {
			Scanner input = new Scanner(FileIO.getTagFile());
			if(!FileIO.getTagFile().getName().startsWith("#")){
				input.nextLine();
			}
			while(input.hasNextLine()){
				userProfileTA.append(input.nextLine() + "\n");
				userProfileTA.setCaretPosition(0);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// This is what is visible by default when the program is started.  This is the GUEST file that everyone can see.
	public void loadGuestProfile(){
		try {
			Scanner input = new Scanner(FileIO.getFile());
			input.nextLine();
			while(input.hasNextLine()){
				profileTF.append(input.nextLine() + "\n");
				profileTF.setCaretPosition(profileTF.getDocument().getLength());
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// This loads a users profile when they signin or when someone searches for them.
	public void loadUserProfile(){
		try {
			Scanner input = new Scanner(FileIO.getFile());
			input.nextLine();
			while(input.hasNextLine()){
				userProfileTA.append(input.nextLine() + "\n");
				userProfileTA.setCaretPosition(0);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//  Default log in screen. 
	public void guestSignon(){
		FileIO newUser = new FileIO();
		newUser.setNewUser(userObj);
		if(newUser.newUserCheck()){
			connectBTN.setEnabled(true);
			disconnectBTN.setEnabled(false);
			loadGuestProfile();
			postitTF.requestFocus();
		}
	}
	
	// This validates a users password.  If the password is correct, changes the GUI components to accept privileged user actions.
	@SuppressWarnings("deprecation")
	public void validUserPass(){
		userObj.setUser(userTF.getText());
		userObj.setPassword(passwordTF.getText());
		if((userObj.getUser().isEmpty()) || (userObj.getPassword().isEmpty())){
			JOptionPane.showMessageDialog(null, "Invaild Username or Password");
			if(userObj.getPassword().isEmpty()){
				passwordTF.requestFocus();
			}
			if(userObj.getUser().isEmpty()){
				userTF.requestFocus();
			}
		}else{
			FileIO newUser = new FileIO();
			newUser.setNewUser(userObj);
			if(newUser.newUserCheck()){
				connectBTN.setEnabled(false);
				disconnectBTN.setEnabled(true);
				passwordTF.setVisible(false);
				passwordLBL.setVisible(false);
				btnPrivatePost.setEnabled(true);
				userLBL.setText("Signed is as:");
				loadUserProfile();
				lblEnterTextBelow.setText("Currently logged in as:  " + userObj.getUser());
				postitTF.setEditable(true);
				postitBTN.setEnabled(true);
				topicTF.setEditable(true);
				topicBTN.setEnabled(true);
				postitTF.requestFocus();
			}
		}
	}
}