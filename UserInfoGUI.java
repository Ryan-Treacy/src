import java.awt.BorderLayout;
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
import javax.swing.JPasswordField;


public class UserInfoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void buildLogin(){
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
		
		JLabel lblUserName = new JLabel("User:");
		lblUserName.setBounds(60, 82, 99, 15);
		contentPane.add(lblUserName);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(28, 123, 99, 15);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(129, 80, 255, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				validUserPass();
			}
		});
		btnConnect.setBounds(165, 191, 117, 25);
		contentPane.add(btnConnect);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 121, 258, 19);
		contentPane.add(passwordField);
	}
	
	public void validUserPass(){
		UserInfo userObj = new UserInfo();
		userObj.setUser(textField.getText());
		userObj.setPassword(passwordField.getText());
		if((userObj.getUser().isEmpty()) || (userObj.getPassword().isEmpty())){
			JOptionPane.showMessageDialog(null, "Invaild Username or Password");
		}else{
			newUserCheck();
		}
	}
	
	public void newUserCheck(){
		// file input output stuff here.
	}
}
