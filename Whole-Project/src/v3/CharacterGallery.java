package v3;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * 
 * @author Austin Huang
 * Gallery of characters.
 */
public class CharacterGallery {
	ArrayList<Character> characterList;
	String characterStats = "";
/**
 * Uses passed in characters to create an array list of characters.
 * @param characters
 */
	public CharacterGallery(Character...characters){
		characterList = new ArrayList<Character>();
		for(Character character : characters){
			characterList.add(character);
		}
	}
	/**
	 * Gets stats of each character using lamda forEach.
	 */
	public void gatherStats(){
		characterList.forEach(character -> characterStats = characterStats + character.getStats() + "\n \n");  
	}
	/*
	 * Test method for individual character.
	 */
	public void printS(){
		System.out.println(characterList.get(0).getStats());
	}
	
	/**
	 * Test method as well for gatherStats().
	 */
	public void displayTest(){
		gatherStats();
		System.out.println(characterStats);

	}
	/**
	 * Displays stats of each characters in a JOptionPane.
	 */
	public void displayStats(){
		if(characterStats.equals("")){
			gatherStats();
		}
		JTextArea textArea = new JTextArea(characterStats);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(300,600));
		JOptionPane.showMessageDialog(null, scrollPane, "Characters", 1);
	}
}