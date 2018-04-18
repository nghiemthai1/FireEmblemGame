import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//implement actionlistener
public class LoginGUI extends JFrame {
 
 /**
  * 
  */
 private static final long serialVersionUID = 1L;
 private LoginGUI thisGUI;

 private JFrame loginFrame = new JFrame("Login or Registration");

 private JPanel loginPanel;
 private JPanel buttonPanel;
 private JPanel titlePanel;
 
 private JLabel userLabel;
 private JLabel passwordLabel;
 private JLabel titleLabel;
 
 private JButton loginButton;
 private JButton registerButton;
 
 
 private JTextField usernameField, passwordField;
 
 private UserCredentials userCred;
 String fileName = "";
 static String fileLocation ="Users.txt";
 
 UserCredentials user;

 public LoginGUI() {
  setUP();
  addComponents();
  addActionListeners();
  JOptionPane.showMessageDialog(null, "Currently game is in beta testing. Platinum membership is given to all players for this period!");

  loginFrame.setVisible(true);
  
 }
 
 public void setUP() {
	 loginFrame.setLayout(new BorderLayout());
	 loginFrame.setSize(500,500);
	 loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  // loginFrame.setSize();

	}
 
 private void addComponents() {

  

  // Creation of a Panel to contain the JLabels
	 titleLabel = new JLabel("Fire Emblem");
	 userLabel = new JLabel("Username");
	 passwordLabel = new JLabel("Password");
	 
	 
	 usernameField = new JTextField(8);
	 passwordField = new JTextField(8);
	 
	 registerButton = new JButton("Register");
	 loginButton = new JButton("Login");
	  
	 titlePanel = new JPanel();
	 titlePanel.add(titleLabel);
	 
	 loginPanel = new JPanel();
	 loginPanel.add(userLabel);
	 loginPanel.add(usernameField);
	 loginPanel.add(passwordLabel);
	 loginPanel.add(passwordField);
	 
	 buttonPanel = new JPanel();
	 buttonPanel.add(registerButton);
	 buttonPanel.add(loginButton);
  
  
  
     loginFrame.add(loginPanel,BorderLayout.CENTER);
     loginFrame.add(titlePanel,BorderLayout.NORTH);
     loginFrame.add(buttonPanel,BorderLayout.SOUTH);

  
 /**

  // TextFields Panel Container
  panelForTextFields = new JPanel();
  panelForTextFields.setLayout(null);
  panelForTextFields.setLocation(110, 40);
  panelForTextFields.setSize(100, 70);
  loginFrame.add(panelForTextFields);

  // Username Textfield
  usernameField = new JTextField(8);
  usernameField.setLocation(0, 0);
  usernameField.setSize(100, 30);
  panelForTextFields.add(usernameField);

  // Login Textfield
  loginField = new JTextField(8);
  loginField.setLocation(0, 40);
  loginField.setSize(100, 30);
  panelForTextFields.add(loginField);

  // Creation of a Panel to contain the completion JLabels
  completionPanel = new JPanel();
  completionPanel.setLayout(null);
  completionPanel.setLocation(240, 35);
  completionPanel.setSize(70, 80);
  loginFrame.add(completionPanel);

  // Username Label
  userLabel = new JLabel();
  userLabel.setForeground(Color.red);
  userLabel.setLocation(0, 0);
  userLabel.setSize(100, 40);
  completionPanel.add(userLabel);

  // Login Label
  passLabel = new JLabel();
  passLabel.setForeground(Color.red);
  passLabel.setLocation(0, 40);
  passLabel.setSize(70, 40);
  completionPanel.add(passLabel);

  // Button for Logging in
  loginButton = new JButton("Login");
  loginButton.setLocation(130, 120);
  loginButton.setSize(80, 30);
  loginButton.addActionListener(this);
  loginFrame.add(loginButton);

  registerButton = new JButton("Register");
  registerButton.setLocation(25, 120);
  registerButton.setSize(90, 30);
  registerButton.addActionListener(this);
  loginFrame.add(registerButton);
*/
 }
 
 public void addActionListeners(){
	 loginButton.addActionListener(new LoginListener());
	 registerButton.addActionListener(new RegisterListener());
 }
 
 public void close(){
	 loginFrame.setVisible(false);
	 loginFrame.dispose();
	}
 
 public class LoginListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		   if ((usernameField.getText() != null) && isValid(usernameField.getText())) {
		    userLabel.setForeground(Color.green);
		    userLabel.setText("yes");
		    userCred = UserCredentials.deserialize(usernameField.getText());
		    if (passwordField.getText().equals(userCred.getPassword())) {
		     try {
		    	 
		    	 new StartMenuGUI(userCred);
		    	 close();
		      
		     } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     }
		    }
		    
		   } else {
		    JOptionPane.showMessageDialog(null, "Invalid!");
		   }
	}
 }
	
	public class RegisterListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (!isValid(usernameField.getText())) {
			    JOptionPane.showMessageDialog(null, new DuplicateUsernameException().errormsg);
			   } else {
			    userLabel.setForeground(Color.green);
			    userLabel.setText("you are good to go");
			    UserCredentials newCred = new UserCredentials(usernameField.getText(), usernameField.getText());
			    userCred = newCred;
			    userCred.serialize();
			   }
			  }

			 
			
		}
		
	
	 
 
 
 public void actionPerformed(ActionEvent arg0) {

  Component frame = new JOptionPane();
  if (arg0.getSource() == loginButton) {
   if ((usernameField.getText() != null) && isValid(usernameField.getText())) {
    userLabel.setForeground(Color.green);
    userLabel.setText("yes");
    userCred = UserCredentials.deserialize(usernameField.getText());
    if (passwordField.getText().equals(userCred.getPassword())) {
     try {
    	 
    	 new StartMenuGUI(userCred);
    	 close();
      
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
    }
    else{
     
    }
   } else {
    JOptionPane.showMessageDialog(frame, "Invalid!");
   }

  }
  else if (arg0.getSource() == registerButton) {
   if (!isValid(usernameField.getText())) {
    JOptionPane.showMessageDialog(frame, new DuplicateUsernameException().errormsg);
   } else {
    userLabel.setForeground(Color.green);
    userLabel.setText("you are good to go");
    UserCredentials newCred = new UserCredentials(usernameField.getText(), usernameField.getText());
    this.userCred = newCred;
    userCred.serialize();
   }
  }

 }

 public static boolean isValid(String username) {
  boolean valid = false;
  File f = new File(fileLocation);
  String[] userNameList = f.list();
  username += ".txt";
  if(userNameList == null){
   return true;
  }
  for (String fileName : userNameList) {
   if (fileName.equals(username)) {
    valid = true;
   }
  }
  return valid;
 }

}