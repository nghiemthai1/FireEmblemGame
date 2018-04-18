import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartMenuGUI extends JFrame {

	private JFrame startFrame = new JFrame("Fire Emblem");

	private JPanel titlePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private String fileName = "RadDawn.gif";
	private String url;
	private UserCredentials userCredentials;
	CharacterGallery characterGallery;

	JButton startButton;
	JButton tutorialButton;
	JButton characterButton;
	JButton creditsButton;
	JButton exitButton;
	
	StartMenuGUI startMenu;
	
	Knight knight = new Knight(0,0,null,null,null);
	DragonKnight dragonKnight = new DragonKnight(0,0,null,null,null);
	Archer archer = new Archer(0,0,null,null,null);
	Warrior warrior = new Warrior(0,0,null,null,null);
	Healer healer = new Healer(0,0,null,null,null);
	Mage mage = new Mage(0,0,null,null,null);
	Alchemist alchemist = new Alchemist(0,0,null,null,null);
	Assassin assassin = new Assassin(0,0,null,null,null);
	UserCredentials user;

	public StartMenuGUI(UserCredentials userCredentials) throws IOException {
		JOptionPane.showMessageDialog(null, "Game is currently in beta testing. Platinum membership is given to all players for this period!");

		this.userCredentials = userCredentials;
		this.characterGallery = new CharacterGallery(warrior, archer, knight, mage, dragonKnight, healer, alchemist, assassin);
		startMenu = this;
		setUP();
		addComponents();
		placeGif(fileName);
		addListeners();
		startFrame.setVisible(true);
	}
/**
 * Set up for JFrame/GUI.
 */
	public void setUP() {
		startFrame.setSize(950, 400);
		this.setMaximumSize(new Dimension(20, 40));
		startFrame.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(6, 1));
		startFrame.setName("Fire Emblem");

		startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
/**
 * Adds components to JFrame.
 */
	public void addComponents() {

		String platMember;
		if (userCredentials.isPlatinumMembership()) {
			platMember = "True";
		} else {
			platMember = "False";
		}

		buttonPanel.add(
				new JLabel("User: " + userCredentials.getUser().toUpperCase() + "\n Platinum Member: " + platMember));
		startButton = new JButton("Start Game");
		tutorialButton = new JButton("Tutorial");
		characterButton = new JButton("Characters");
		creditsButton = new JButton("Credits");
		exitButton = new JButton("Exit");
		startButton.setPreferredSize(new Dimension(100, 100));
		tutorialButton.setPreferredSize(new Dimension(100, 100));
		characterButton.setPreferredSize(new Dimension(100, 100));
		creditsButton.setPreferredSize(new Dimension(100, 100));
		exitButton.setPreferredSize(new Dimension(100, 100));

		buttonPanel.add(startButton);
		buttonPanel.add(tutorialButton);
		buttonPanel.add(characterButton);
		buttonPanel.add(creditsButton);
		buttonPanel.add(exitButton);

		startFrame.add(buttonPanel, BorderLayout.WEST);

	}

	/**
	 * Creates listener for exit button.
	 * @author Austin Huang
	 *
	 */
	public class exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			System.exit(0);

		}
	}
/**
 * Creates tutorial based on text.
 * @author Austin Huang
 *
 */
	public class tutorialListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"How to play: \n Populate the board by using the dropdown windows on either side."
							+ "\n The window will populate the closest space to it"
							+ "\n Keep in mind each unit has a cost, and you only have a certain amount of money."
							+ "\n Click the 'end phase' when you are done choosing your units."
							+ "\n The battle will now begin." + "\n Click a unit to perform an action"
							+ ", \n You may only move up to 2 units a turn and only attack or heal within that units range."
							+ "\n \n You may look for further information about character classes by selecting Characters in the start menu.");
		}
	}

	/**
	 * Creates new game client.
	 * @author Austin Huang
	 *
	 */
	public class startListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new GameClient(startMenu);
			startFrame.setVisible(false);
			startFrame.dispose();
		}
	}
/**
 * Creates credit display.
 * @author Austin Huang
 *
 */
	public class creditListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"Austin Huang, Matt Rodriguez, Ardit Pranvoku, and Robert worked on this game."
					+ "\n Beta Testers: Alex Tsai and Seamus Plunkett");
		}
	}

	/**
	 * Displays stats of characters.
	 * @author Austin Huang
	 *
	 */
	public class characterListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			characterGallery.displayStats();
		}
	}
/**
 * Adds listeners to buttons.
 */
	public void addListeners() {
		exitButton.addActionListener(new exitListener());
		tutorialButton.addActionListener(new tutorialListener());
		characterButton.addActionListener(new characterListener());
		creditsButton.addActionListener(new creditListener());
		startButton.addActionListener(new startListener());
	}
	
	/**
	 * Gets an image label base on file passed in to use for GUI.
	 * @param fileName
	 * @return
	 */
	public JLabel getImageLabel(String fileName){
		BufferedImage image;
		JLabel newPic = null;
		try {
			image = ImageIO.read(new File(fileName));
			ImageIcon imageIcon = new ImageIcon(image);
			newPic = new JLabel(imageIcon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return newPic;
	}
	
	/**
	 * Uses image to add to GUI.
	 * @param fileName
	 * @return
	 */
	
	public void placeImage(String fileName) {
		JLabel titlePic = getImageLabel(fileName);
		titlePic.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		startFrame.add(titlePic, BorderLayout.CENTER);
		
	}

	/**
	 * Uses gif to add to GUI.
	 * @param fileName
	 * @return
	 */
	public void placeGif(String fileName) throws MalformedURLException {
		ImageIcon imageIcon;
		try {

			URL gifURL = new File(fileName).toURI().toURL();
			Icon icon = new ImageIcon(gifURL);
			JLabel titleGif = new JLabel(icon);
			titleGif.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			JFrame gifFrame = new JFrame();
			startFrame.add(titleGif, BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public JFrame getStartFrame() {
		return startFrame;
	}
}