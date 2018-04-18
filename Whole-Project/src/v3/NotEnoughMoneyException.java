package v3;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;
/**
 * Custom exception used to make sure user does not purchase characters over the set limit specified.
 * @author Austin Huang
 *
 */
public class NotEnoughMoneyException extends Exception{
 private String player;
 private int moneyLeft;
	
 public NotEnoughMoneyException(String player, int moneyLeft){
	 this.player = player;
	 this.moneyLeft = moneyLeft;
 }
 
 public void displayMessage(){
	 JOptionPane.showInputDialog(null,
				"Not enough for this character. " + player + " has only " + moneyLeft
						+ " left.\nNow think about your poor financial decisions and write down your thoughts. Please and thank you...",
				"Character Options", JOptionPane.DEFAULT_OPTION);
 }
 
 
}