package v3;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Starts the game has some control over the game GUI.
 * 
 * @author Matthew Rodriguez
 *
 */
public class GameClient {

	static final int DEFAULT_WIDTH = 15;
	static final int DEFAULT_DEPTH = 12;

	private List<Character> characters;

	private Field field;
	private GUI gui;
	private StartMenuGUI startGUI;
	
	private int number;

	List<Character> newCharacters = new ArrayList<Character>();
	private PvEGUI pveGui;

	/**
	 * Construct a field and starts the game.
	 * 
	 * @param gui
	 *            the start menu that started the game.
	 */
	public GameClient(StartMenuGUI gui, int n) {
		this(DEFAULT_DEPTH, DEFAULT_WIDTH, gui,n);
	}

	/**
	 * Create a the game with given fields.
	 * 
	 * @param depth
	 *            Depth of the grid. Must be greater than zero.
	 * @param width
	 *            Width of the grid. Must be greater than zero.
	 * @param gui
	 *            The start menu that starts the game.
	 */
	public GameClient(int depth, int width, StartMenuGUI gui, int n) {
		if (width <= 0 || depth <= 0) {
			System.out.println("The dimensions must be greater than zero.");
			System.out.println("Using default values.");
			depth = DEFAULT_DEPTH;
			width = DEFAULT_WIDTH;
		}

		characters = new ArrayList<Character>();
		this.field = new Field(depth, width);

		// Create a view of the state of each location in the field.
		// @author: THAI NGHIEM
		this.number = n;
		if (number==0){
			this.gui = new GUI(depth, width, this.field, this, gui);
		}
		else if (number==1){
			this.pveGui = new PvEGUI(depth, width, this.field, this, gui);
		}

		// Setup a valid starting point.
		reset();
	}

	/**
	 * Run the one turn of the game
	 */
	public void runTurn() {

		for (Iterator<Character> it = characters.iterator(); it.hasNext();) {
			Character character = it.next();
			if (character.getHP() == 0) {
				it.remove();
			}
		}
		newCharacters.clear();

		// gui.runTurn();
		// gui.showStatus(step, field);
	}

	/**
	 * Reset the game to a starting position.
	 */
	public void reset() {
		characters.clear();
		field.clear();

		// Show the starting state in the view.
		if (number==0){
		gui.populate();
		}
		else if(number==1){
		pveGui.populate();
		}
	}

}
