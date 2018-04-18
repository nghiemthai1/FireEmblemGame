import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class UserCredentials implements Serializable {

 private String username;
 private String password;
 private boolean platinumMembership = true;
 static String fileName = "Users.txt";
 
 private static final long serialVersionUID = 7041561544249945600L;

 public UserCredentials(String username, String password) {
  super();
  this.username = username;
  this.password = password;
  this.platinumMembership = true; //Beta testing!!! Subject to change!!!
 }

 public String getUsername() {
  return username;
 }

 public boolean isPlatinumMembership() {
  return platinumMembership;
 }

 public void isPlatinumMember() {
  platinumMembership = true;
 }
 
 public String getPassword(){
  return password;
 }
 
 public String getUser(){
  return this.username;
 }

 public void serialize() {
  try {
   FileOutputStream fos = new FileOutputStream(fileName);
   ObjectOutputStream oos = new ObjectOutputStream(fos);
   oos.writeObject(this);
   oos.close();
   fos.close();
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

 public static UserCredentials deserialize(String username) {
  UserCredentials s = null;
  try {
   FileInputStream fis = new FileInputStream(fileName);
   ObjectInputStream ois = new ObjectInputStream(fis);
   s = (UserCredentials) (ois.readObject());
   ois.close();
   fis.close();
  } catch (Exception e) {
   e.printStackTrace();;
  }
  return s;
 }

}