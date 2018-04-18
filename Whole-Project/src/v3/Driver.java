package v3;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JOptionPane;

public class Driver {
 
 

 public static void main(String[] args) {
  
  /**
  JOptionPane.showMessageDialog(null, "Currently game is in beta testing. Platinum membership is given to all players for this period!");
  try {
   new LoginGUI();
  } catch (MalformedURLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  */
  
	try {
		new StartMenuGUI(new UserCredentials("Myers", "adoop", "C:\\users\\"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

   
  Music music = new Music("TheHydraeansWrath.wav", "StalkingMenace.wav", "TheDevoted.wav", "RadiantDawnOpening.wav");
  //Music music = new Music("Drowning.wav", "icecream.wav", "rolex.wav", "xotourlife.wav");
  music.run();


 }

}