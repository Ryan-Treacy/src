import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JPasswordField;


public class UserInfoGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private UserInfo userObj = new UserInfo();

	/**
	 * Launch the application.
	 */
	public void buildLogin(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoGUI frame = new UserInfoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInfoGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(28, 82, 99, 15);
		contentPane.add(lblUserName);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(28, 123, 99, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(passwordField.getText().isEmpty()){
					passwordField.requestFocus();
				}else{
					validUserPass();
				}
			}
		});
		textField.setBounds(129, 80, 255, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setToolTipText("Click to sign in/sign up");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validUserPass();
			}
		});
		btnConnect.setBounds(165, 191, 117, 25);
		contentPane.add(btnConnect);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(textField.getText().isEmpty()){
					textField.requestFocus();
				}else{
					validUserPass();
				}
			}
		});
		passwordField.setBounds(126, 121, 258, 19);
		contentPane.add(passwordField);
		
		JLabel lblEnterUsernameAnd = new JLabel("Enter Username and Password");
		lblEnterUsernameAnd.setBounds(101, 26, 255, 19);
		contentPane.add(lblEnterUsernameAnd);
	}
	
	public void validUserPass(){
		userObj.setUser(textField.getText());
		userObj.setPassword(passwordField.getText());
		if((userObj.getUser().isEmpty()) || (userObj.getPassword().isEmpty())){
			JOptionPane.showMessageDialog(null, "Invaild Username or Password");
			if(userObj.getPassword().isEmpty()){
				passwordField.requestFocus();
			}
			if(userObj.getUser().isEmpty()){
				textField.requestFocus();
			}
		}else{
			FileIO newUser = new FileIO();
			newUser.setNewUser(userObj);
			newUser.newUserCheck();
		}
	}
}