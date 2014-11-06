import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class UserInfoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void buildLogin() {
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
		
		JLabel lblWelcomeToUmw = new JLabel("Welcome to UMW Connect!");
		lblWelcomeToUmw.setBounds(108, 24, 220, 15);
		contentPane.add(lblWelcomeToUmw);
				
		JButton btnNewButton = new JButton("Sign On");
		btnNewButton.setBounds(244, 203, 194, 25);
		btnNewButton.setEnabled(true);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("User ID:");
		lblNewLabel.setBounds(59, 77, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(40, 115, 89, 15);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(129, 75, 199, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 113, 199, 19);
		contentPane.add(passwordField);
		
	}
}