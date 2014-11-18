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
	 * Initialize the contents of the frame.
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
		profileSP.setBounds(36, 97, 603, 243);
		frmUmwCompsciPost.getContentPane().add(profileSP);
		
		profileTF = new JTextArea();
		profileTF.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		profileTF.setEditable(false);
		profileSP.setViewportView(profileTF);
		profileTF.setWrapStyleWord(true);
		profileTF.setLineWrap(true);
		
		userProfileSP = new JScrollPane();
		userProfileSP.setBounds(36, 381, 603, 108);
		frmUmwCompsciPost.getContentPane().add(userProfileSP);
		
		userProfileTA = new JTextArea();
		userProfileTA.setEditable(false);
		userProfileTA.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		userProfileTA.setWrapStyleWord(true);
		userProfileSP.setViewportView(userProfileTA);
		
		topicTF = new JTextField();
		topicTF.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		topicTF.setBounds(36, 344, 158, 25);
		frmUmwCompsciPost.getContentPane().add(topicTF);
		topicTF.setColumns(10);
		
		topicBTN = new JButton("Search");
		topicBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//enter code to open #topics
			}
		});
		topicBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		topicBTN.setBounds(195, 344, 91, 25);
		frmUmwCompsciPost.getContentPane().add(topicBTN);
		
		searchLBL = new JLabel("Search for a @User or #Topic.");
		searchLBL.setBounds(291, 348, 600, 21);
		frmUmwCompsciPost.getContentPane().add(searchLBL);
		searchLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		
		btnPrivatePost = new JButton("Private Post");
		btnPrivatePost.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnPrivatePost.setBounds(253, 64, 133, 25);
		btnPrivatePost.setEnabled(false);
		frmUmwCompsciPost.getContentPane().add(btnPrivatePost);
	}
	
	public void updateProfile(){
		String temp = postitTF.getText();
		if(!temp.equals("")){
			FileIO.updateFile(userObj.getUser() + ": " +  temp + "\n", FileIO.getFile());
			if(!userObj.getUser().contentEquals("GUEST")){
				FileIO.updateFile(userObj.getUser() + ": " + temp + "\n", FileIO.getGuestFile());
			}
			profileTF.append(userObj.getUser() + ": " + temp + "\n");
			profileTF.setCaretPosition(profileTF.getDocument().getLength());
			if(!userObj.getUser().contentEquals("GUEST")){
				userProfileTA.append(userObj.getUser() + ": " + temp + "\n");
				
			}
			postitTF.setText("");
			postitTF.requestFocus();
			//search for user or topic tags 
			Search.searchForTag(temp);
		}else{
			postitTF.requestFocus();
		}
	}
	
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
				postitTF.requestFocus();
			}
		}
	}
}