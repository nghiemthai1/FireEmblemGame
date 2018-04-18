package v3;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * UserCredentials used to save login information as well as platinum membership status.
 * 
 */
public class UserCredentials implements Serializable {

 private String username;
 private String password;
 private boolean platinumMembership;
 private String fileLocation;
 
 private static final long serialVersionUID = 7041561544249945600L;

 public UserCredentials(String username, String password, String fileLocation) {
  super();
  this.fileLocation = fileLocation;
  this.username = username;
  this.password = password;
  this.platinumMembership = true; //Beta testing!!! Subject to change!!!
 }

 public String getUsername() {
  return username;
 }
/**
 * Returns platinum membership.
 * @return
 */
 public boolean getPlatinumMembership() {
  return platinumMembership;
 }
/**
 * Changes platinum membership to true. No set to false, because once you are platinum member you cannot 
 * be demoted to regular member. 
 */
 public void isPlatinumMember() {
  platinumMembership = true;
 }
 
 public String getPassword(){
  return password;
 }
 
 public String getUser(){
  return this.username;
 }
/**
 * Serialize method to store user data, focused primarily to check whether user is platinum member or not.
 */
 public void serialize() {
  try {
   FileOutputStream fos = new FileOutputStream(fileLocation + username + ".ser");
   //System.out.println(fileLocation + username + ".ser");
   //System.out.println(username);
   //System.out.println(password);
   ObjectOutputStream oos = new ObjectOutputStream(fos);
   oos.writeObject(this);
   oos.close();
   fos.close();
  } catch (Exception e) {
	  JOptionPane.showMessageDialog(null, "Your file location is wrong.\nCheck if you typed your computer account name correctly.");
  }
 }
/**
 * Deserialize method to follow serialize.
 * @param username
 * @return
 */
 public static UserCredentials deserialize(String username, String fileLocation) {
  UserCredentials s = null;
  try {
   FileInputStream fis = new FileInputStream(fileLocation + username + ".ser");
   //System.out.println(fileLocation + username + ".ser");
   ObjectInputStream ois = new ObjectInputStream(fis);
   s = (UserCredentials) (ois.readObject());
   ois.close();
   fis.close();
  } catch (Exception e) {
	  JOptionPane.showMessageDialog(null, "Your file location is wrong.\nCheck if you typed your computer account name correctly.");
  }
  return s;
 }

}