

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

public class GameClient
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 15;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 15;

    // List of animals in the field.
    private List<Character> characters;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private GUI gui;
    private StartMenuGUI startGUI;
    
    List<Character> newCharacters = new ArrayList<Character>();
    
    /**
     * Construct a simulation field with default size.
     */
    public GameClient(StartMenuGUI gui)
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH,gui);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public GameClient(int depth, int width, StartMenuGUI gui)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        characters = new ArrayList<Character>();
        this.field = new Field(depth, width);

        // Create a view of the state of each location in the field.
       this.gui = new GUI(depth, width, this.field, this, gui);
        
        // Setup a valid starting point.
        reset();
    }
    
    
    
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void runTurn()
    {
    	step++;
    	
	    for(Iterator<Character> it = characters.iterator(); it.hasNext(); ) {
	    	Character character = it.next();
	    	if(character.getHP() == 0) {
	    		it.remove();
	    	}  
        }
        newCharacters.clear();
        
        //gui.runTurn();
        //gui.showStatus(step, field);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        characters.clear();
        field.clear();  
        
        // Show the starting state in the view.
        gui.populate(field);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate(Character character) {
        characters.add(character);
    }
}
