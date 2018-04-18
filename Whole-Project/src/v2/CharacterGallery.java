import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CharacterGallery {
	ArrayList<Character> characterList;
	String characterStats = "";

	public CharacterGallery(Character...characters){
		characterList = new ArrayList<Character>();
		for(Character character : characters){
			characterList.add(character);
		}
	}

	public void gatherStats(){
		characterList.forEach(character -> characterStats = characterStats + character.getStats() + "\n \n");  
	}
	public void printS(){
		System.out.println(characterList.get(0).getStats());
	}
	public void displayTest(){
		gatherStats();
		System.out.println(characterStats);

	}

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