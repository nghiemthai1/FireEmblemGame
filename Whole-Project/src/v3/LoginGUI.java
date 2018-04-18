package v3;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * Login GUI that allows user to register and login with previous recorded login information.
 * 
 * Note: Did not want to be mean to Robert, but during the presentation he corrected his "we" to "I" which 
 * was not justifiable. The man walked in and created a loginGUI that did not work and did not look up to par 
 * after a few hours and left but claimed the result in the form of "I". Every team member had to work
 * on his initial GUI which ended up having to be redone. Every team member also spoke in the form of "we,"
 * displaying teamwork.
 * 
 */

public class LoginGUI extends JFrame {
 
 /**
  * 
  */
 private static final long serialVersionUID = 1L;
 private LoginGUI thisGUI;

 private JFrame loginFrame = new JFrame("FireEmblem");

 private JPanel loginPanel;
 private JPanel buttonPanel;
 private JPanel titlePanel;
 
 private JLabel userLabel;
 private JLabel passwordLabel;
 private JLabel titleLabel;
 
 private JButton loginButton;
 private JButton registerButton;
 
 private JTextField locationField;
 
 
 private JTextField usernameField, passwordField;
 
 private UserCredentials userCred;
 private String fileName = "";
 private String fileLocation;
 
 UserCredentials user;
/**
 * Constructor for LoginGUI.
 * @throws MalformedURLException
 */
 public LoginGUI() throws MalformedURLException {
  setUP();
  addComponents();
  addActionListeners();
  
  placeGif("loginGif.gif");
  loginFrame.setVisible(true);
  
 }
 
 /**
  * Set up for GUI. Sets loginFrame.
  */
 public void setUP() {
	  try{ 
		    loginFrame.setIconImage(ImageIO.read(new FileInputStream("icon.png")));
		  } 
		  catch (IOException e){
		    e.printStackTrace();
		  }
  loginFrame.setLayout(new BorderLayout());
  loginFrame.setSize(500,500);
  loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  loginFrame.setLocationRelativeTo(null);
   // loginFrame.setSize();

 }
 /**
  * Adds necessary components to loginFrame.
  */
 private void addComponents() {

  

  // Creation of a Panel to contain the JLabels
  
  userLabel = new JLabel("Username");
  passwordLabel = new JLabel("Password");
  
  
  usernameField = new JTextField(8);
  passwordField = new JPasswordField(8);
  ((JPasswordField) passwordField).setEchoChar('*');
  
  registerButton = new JButton("Register");
  loginButton = new JButton("Login");
   
  titlePanel = new JPanel();
  titlePanel.add(userLabel);
  titlePanel.add(usernameField);
  titlePanel.add(passwordLabel);
  titlePanel.add(passwordField);
  
  locationField = new JTextField(8);
  loginPanel = new JPanel();
  buttonPanel = new JPanel();
  buttonPanel.add(new JLabel("Enter your computer account name: "));
  buttonPanel.add(locationField);
  
  buttonPanel.add(registerButton);
  buttonPanel.add(loginButton);
  
  
  
     loginFrame.add(loginPanel,BorderLayout.CENTER);
     loginFrame.add(titlePanel,BorderLayout.NORTH);
     loginFrame.add(buttonPanel,BorderLayout.SOUTH);

  
 }
 /*
  * Adds action listeners to proper buttons.
  */
 public void addActionListeners(){
  loginButton.addActionListener(new LoginListener());
  registerButton.addActionListener(new RegisterListener());
 }
 /**
  * Close method for JFrame.
  */
 public void close(){
  loginFrame.setVisible(false);
  loginFrame.dispose();
 }
 /**
  * Listener for login to check if info is valid.
  * @author Austin Huang
  *
  */
 public class LoginListener implements ActionListener{

 @Override
 public void actionPerformed(ActionEvent arg0) {
  // TODO Auto-generated method stub
		fileLocation = "C:\\Users\\" + locationField.getText() + "\\users\\" ;

     if ((usernameField.getText() != null) && !isValid(usernameField.getText())) {
      userLabel.setForeground(Color.green);
      userCred = UserCredentials.deserialize(usernameField.getText(), fileLocation);
      if (passwordField != null && userCred != null && passwordField.getText().equals(userCred.getPassword())) {
       try {
        
        new StartMenuGUI(userCred);
        close();
        
       } catch (IOException e) {
    	   JOptionPane.showMessageDialog(null, "Your file location is wrong.\nCheck if you typed your computer account name correctly");
       }
      }
      else{
    	  JOptionPane.showMessageDialog(null, "Invalid credentials!");
      }
      
     } else {
      JOptionPane.showMessageDialog(null, "Invalid!");
     }
 }
 }
 /**
  * Listener for register to check if user is able to register.
  * @author Austin Huang
  *
  */
 public class RegisterListener implements ActionListener{

  @Override
  public void actionPerformed(ActionEvent arg0) {
   // TODO Auto-generated method stub
		fileLocation = "C:\\Users\\" + locationField.getText() + "\\users\\" ;
		
   if (!isValid(usernameField.getText())) {
       JOptionPane.showMessageDialog(null, new DuplicateUsernameException().errormsg);
      } else {
       userLabel.setForeground(Color.green);
       userLabel.setText("you are good to go");
       UserCredentials newCred = new UserCredentials(usernameField.getText(), passwordField.getText(), fileLocation);
       userCred = newCred;
       userCred.serialize();
      }
     }

    
   
  }
  
 /**
  * Checks to see if username exists.
  * @param username
  * @return
  */
 public boolean isValid(String username) {
  boolean valid = true;
  File f = new File(fileLocation);
  String[] userNameList = f.list();
  username += ".ser";
  if(userNameList == null || userNameList.length == 0){
   return true;
  }
  for (String fileName : userNameList) {
   if (fileName.equals(username)) {
    valid = false;
   }
  }
  return valid;
 }
 
 /**
  * Places gif in GUI using File IO. 
  * @param fileName
  * @throws MalformedURLException
  */
 public void placeGif(String fileName) throws MalformedURLException {
	  ImageIcon imageIcon;
	  try {

	   URL gifURL = new File(fileName).toURI().toURL();
	   Icon icon = new ImageIcon(gifURL);
	   JLabel titleGif = new JLabel(icon);
	   titleGif.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	   JFrame gifFrame = new JFrame();
	   loginFrame.add(titleGif, BorderLayout.CENTER);
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }

}