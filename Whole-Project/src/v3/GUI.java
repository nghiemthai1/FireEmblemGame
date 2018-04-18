package v3;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("serial")
/**
 * This is the GUI for the game that will be started from the start menu.
 * 
 * @author Matthew Rodriguez
 *
 */
public class GUI extends JFrame {
	private static final String ARCHER = "Archer.jpg";
	private static final String KNIGHT = "Knight.jpg";
	private static final String WARRIOR = "Warrior.jpg";
	private static final String MAGE = "Mage.jpg";
	private static final String HEALER = "Priest.jpg";
	private static final String DRAGONKNIGHT = "DK.jpg";
	private static final String ALCHEMIST = "Alchemist.jpg";
	private static final String ASSASSIN = "Assassin.jpg";

	private static final String P1_WIN = "BlueTeamVictory.gif";
	private static final String P2_WIN = "RedTeamVictory.gif";

	private static final int STARTING_CHARS = 18;
	private static final int NUMBER_OF_CLASSES = 9;
	private static final int SIZE_OF_COMBOBOX = 400;
	private static final String EMPTY_SPACE_FILE = "Grass.jpg";
	private static final String MOVE_SPACE_FILE = "BlueGrass.jpg";
	private static final String ATTACK_SPACE_FILE = "RedGrass.jpg";
	private static final int STARTING_MONEY = 15;
	private static final String PLAYER1_PIC = "Player1.jpg";
	private static final String PLAYER2_PIC = "player2.jpg";

	private static final int PLAYER_HEIGHT = 100;
	private static final int PLAYER_WIDTH = 350;

	private static final int SPACE_WIDTH = 170;
	private static final int SPACE_HEIGHT = 65;

	private JPanel board = new JPanel();
	private JButton nextTurn;
	protected int player1Money;
	protected int player2Money;
	protected String playerTurn = "";
	private JLabel p1WinPanel;
	private JLabel p2WinPanel;

	private JPanel moneyPanel;
	private JPanel moneyPanelP1;
	private JPanel moneyPanelP2;
	private JLabel p1Pic;
	private JLabel p2Pic;
	private JLabel p1phrase;
	private JLabel p2phrase;

	private LinkedHashMap<JComboBox<Character>, Location> comboBoxes = new LinkedHashMap<JComboBox<Character>, Location>();
	private Character[] characters;

	private Object[][] fieldLayout;
	private int height;
	private int width;
	private Field field;
	private int step = 1;
	private GameClient gc;
	private StartMenuGUI gui;

	private Map<Character, Image> images;

	/**
	 * Constructor that creates the GUI.
	 * 
	 * @param height
	 *            The amount of rows that the board will contain.
	 * 
	 * @param width
	 *            The amount of columns the board will contain.
	 * 
	 * @param field
	 *            The field the GUI will be interacting with.
	 * 
	 * @param gc
	 *            The game client controlling the game.
	 * 
	 * @param gui
	 *            The start menu this GUI will interact with.
	 */
	public GUI(int height, int width, Field field, GameClient gc, StartMenuGUI gui) {
		this.gui = gui;
		this.player1Money = STARTING_MONEY;
		this.player2Money = STARTING_MONEY;
		this.gc = gc;
		this.height = height;
		this.width = width;
		images = new LinkedHashMap<Character, Image>();
		this.field = field;
		fieldLayout = new Object[height][width];

		setTitle("PvP mode");
		JLabel turnLabel = new JLabel("", JLabel.CENTER);
		setLocation(100, 50);

		board.setLayout(new GridBagLayout());
		nextTurn = new JButton("End Phase");

		populateArray();
		Container contents = getContentPane();
		contents.add(turnLabel, BorderLayout.NORTH);
		contents.add(board, BorderLayout.CENTER);
		contents.add(nextTurn, BorderLayout.SOUTH);
		addActionListener();
		pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(900, 700));
		this.setLocationRelativeTo(null);
		moneyPanel = new JPanel();
		moneyPanelP1 = new JPanel();
		moneyPanelP2 = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//THAI NGHIEM
		  try{ 
			    this.setIconImage(ImageIO.read(new FileInputStream("icon.png")));
			  } 
			  catch (IOException e){
			    e.printStackTrace();
			  }
	}

	/**
	 * Populates the array that contains all the possible characters.
	 */
	public void populateArray() {

		for (int col = 0; col < height; col += (height - 1)) {
			for (int row = 0; row < width; row++) {
				characters = new Character[NUMBER_OF_CLASSES];
				String player = "";
				String playerPic = "";
				if (col == 0) {
					player = "Player 1";
					playerPic = "Player_1_";
				} else {
					player = "Player 2";
					playerPic = "Player_2_";
				}
				characters[0] = null;
				characters[1] = new Warrior(row, col, player, this.field, playerPic + WARRIOR);
				setImage(characters[1]);
				characters[2] = new Archer(row, col, player, this.field, playerPic + ARCHER);
				setImage(characters[2]);
				characters[3] = new Mage(row, col, player, this.field, playerPic + MAGE);
				setImage(characters[3]);
				characters[4] = new DragonKnight(row, col, player, this.field, playerPic + DRAGONKNIGHT);
				setImage(characters[4]);
				characters[5] = new Healer(row, col, player, this.field, playerPic + HEALER);
				setImage(characters[5]);
				characters[6] = new Knight(row, col, player, this.field, playerPic + KNIGHT);
				setImage(characters[6]);
				characters[7] = new Alchemist(row, col, player, this.field, playerPic + ALCHEMIST);
				setImage(characters[7]);
				characters[8] = new Assassin(row, col, player, this.field, playerPic + ASSASSIN);
				setImage(characters[8]);

				JComboBox<Character> boxToAdd = new JComboBox<Character>(characters);
				boxToAdd.setMinimumSize(new Dimension(SIZE_OF_COMBOBOX, SIZE_OF_COMBOBOX));
				comboBoxes.put(boxToAdd, new Location(col, row));
				boxToAdd.addActionListener(new DropDownListens());
			}
		}
	}

	/**
	 * Creates a JLabel with a picture based on a given file name.
	 * 
	 * @param fileName
	 *            The file that contains the picture.
	 * 
	 * @return The JLabel containing the picture.
	 */
	private JLabel makeJLabelPic(String fileName) {
		JLabel emptySpace = new JLabel(
				new ImageIcon(loadImage(fileName).getScaledInstance(SPACE_WIDTH, SPACE_HEIGHT, Image.SCALE_SMOOTH)));
		emptySpace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return emptySpace;
	}

	/**
	 * Creates a custom button with a picture holding a given character and
	 * their given location.
	 * 
	 * @param character
	 *            The character that the button will hold.
	 * 
	 * @param location
	 *            The characters respective location.
	 * 
	 * @return The button with its respective character and location that has
	 *         the appearance of a picture.
	 */
	private JReferencingButtonTwo<Character, Location> makeMoveSpace(Character character, Location location) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(MOVE_SPACE_FILE));
			JReferencingButtonTwo<Character, Location> picLabel = new JReferencingButtonTwo<Character, Location>(
					new ImageIcon(image.getScaledInstance(SPACE_WIDTH, SPACE_HEIGHT, Image.SCALE_SMOOTH)), character,
					location);
			picLabel.setMinimumSize(new Dimension(140, 65));
			picLabel.setMaximumSize(new Dimension(140, 65));
			picLabel.addActionListener(new MoveButtonListens());
			return picLabel;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Creates a button that holds a character that will be doing an action and
	 * the character that will be acted on.
	 * 
	 * @param character
	 *            The character that will be doing an action.
	 * @param charToActOn
	 *            The character that will be acted on.
	 * @param heal
	 *            determines the characters action.
	 * @return The button with its respective character and character that will
	 *         be acted on with the appearance of a picture.
	 */
	private JReferencingButtonTwo<Character, Character> makeActionSpace(Character character, Character charToActOn,
			boolean heal) {
		ActionListener listener;
		if (heal)
			listener = new HealButtonListens();
		else
			listener = new AttackButtonListens();
		JReferencingButtonTwo<Character, Character> picLabel = new JReferencingButtonTwo<Character, Character>(
				new ImageIcon(images.get(charToActOn)), character, charToActOn);
		picLabel.setMinimumSize(new Dimension(65, 65));
		picLabel.setMaximumSize(new Dimension(65, 65));
		picLabel.addActionListener(listener);
		return picLabel;
	}

	/**
	 * Creates an image based on a given character.
	 * 
	 * @param character
	 *            The character that hold the file name for the image that will
	 *            be set.
	 */
	public void setImage(Character character) {
		images.put(character,
				loadImage(character.getFileName()).getScaledInstance(SPACE_WIDTH, SPACE_HEIGHT, Image.SCALE_SMOOTH));
	}

	/**
	 * Creates a buffered image based on a file name.
	 * 
	 * @param fileName
	 *            The file name of the image that will be loaded.
	 * @return The buffered image that loads the picture.
	 */
	public BufferedImage loadImage(String fileName) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(fileName));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Creates a button that holds a character.
	 * 
	 * @param character
	 *            The character that the button will hold. This character also
	 *            holds the file name to the picture that the button will
	 *            display.
	 * @return A button holding a character that displays the characters
	 *         picture.
	 */
	private JReferencingButton<Character> getImage(Character character) {
		JReferencingButton<Character> picLabel = new JReferencingButton<Character>(new ImageIcon(images.get(character)),
				character);
		picLabel.setMinimumSize(new Dimension(65, 65));
		picLabel.setMaximumSize(new Dimension(65, 65));
		return picLabel;
	}

	/**
	 * Creates a picture of Player 1 and Player 2.
	 */
	private void setPlayerPic() {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(PLAYER1_PIC));
			p1Pic = new JLabel(new ImageIcon(image.getScaledInstance(PLAYER_WIDTH, PLAYER_HEIGHT, Image.SCALE_SMOOTH)));

			image = ImageIO.read(new File(PLAYER2_PIC));
			p2Pic = new JLabel(new ImageIcon(image.getScaledInstance(PLAYER_WIDTH, PLAYER_HEIGHT, Image.SCALE_SMOOTH)));
			moneyPanelP1.add(p1Pic);
			moneyPanelP2.add(p2Pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		moneyPanelP2.setVisible(false);
		moneyPanelP1.setVisible(false);
	}

	/**
	 * Shows either the player 1 picture or the player 2 picture based on the
	 * turn.
	 * 
	 * @param turn
	 *            the amount of turns that has occured so far/
	 */
	public void showStatus(int turn) {
		gc.runTurn();
		if (turn % 2 == 0) {
			if (playerTurn.equals("Player 2"))
				this.step++;
			playerTurn = "Player 2";
		} else {
			if (playerTurn.equals("Player 1"))
				this.step++;
			playerTurn = "Player 1";

		}

		if (step == 1) {
			setPlayerPic();
			moneyPanelP1.remove(p1phrase);
			moneyPanelP2.remove(p2phrase);
		}

		if (playerTurn.equals("Player 1")) {
			moneyPanelP2.setVisible(false);
			moneyPanelP1.setVisible(true);
		} else {
			moneyPanelP1.setVisible(false);
			moneyPanelP2.setVisible(true);
		}

		moneyPanel.setMaximumSize(new Dimension(100, 100));
		moneyPanel.repaint();

		setBoard();

	}

	/**
	 * Sets the grid bag constraints to default settings for this game.
	 * 
	 * @param c
	 *            The grid bad constraints that will be set.
	 * @param row
	 *            The row that the constraints will be set to.
	 * @param col
	 *            The column that the constraints will be set to.
	 */
	public void getConstraints(GridBagConstraints c, int row, int col) {
		c.gridx = row;
		c.gridy = col;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
	}

	/**
	 * Sets up the board to display all of the pictures.
	 */
	public void setBoard() {
		board.removeAll();
		if (!isVisible()) {
			setVisible(true);
		}
		int player1Count = 0;
		int player2Count = 0;
		for (int row = 0; row < field.getDepth(); row++) {
			for (int col = 0; col < field.getWidth(); col++) {
				Object character = field.getObjectAt(row, col);
				GridBagConstraints c = new GridBagConstraints();
				getConstraints(c, row, col);
				if (character != null) {
					if (((Character) character).getPlayer().equals(playerTurn)
							&& ((Character) character).getReadyToAttack()) {
						JReferencingButton<Character> charToAdd = getImage((Character) character);
						charToAdd.addActionListener(new CharacterListens());
						board.add(charToAdd, c);
						fieldLayout[row][col] = charToAdd;
						if (((Character) character).getPlayer().equals("Player 1"))
							player1Count++;
						if (((Character) character).getPlayer().equals("Player 2"))
							player2Count++;
					} else {
						((Character) character).setReadyToAttack(true);
						((Character) character).setReadyToMove(true);
						JReferencingButton<Character> charToAdd = getImage((Character) character);
						charToAdd.addActionListener(new EnemyListens());
						board.add(charToAdd, c);
						fieldLayout[row][col] = charToAdd;
						if (((Character) character).getPlayer().equals("Player 1"))
							player1Count++;
						if (((Character) character).getPlayer().equals("Player 2"))
							player2Count++;
					}
				} else {
					JLabel spaceToAdd = makeJLabelPic(EMPTY_SPACE_FILE);
					board.add(spaceToAdd, c);
					fieldLayout[row][col] = spaceToAdd;
				}

				board.updateUI();
			}
		}
		if (player1Count == 0 && step > 1) {
			win(false);
		}
		if (player2Count == 0 && step > 1) {
			win(true);
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Populates the board with images.
	 */
	public void populate() {
		board.removeAll();
		if (!isVisible()) {
			setVisible(true);
		}

		// turnLabel.setText(TURN_LABEL + turn);
		moneyPanel.setLayout(new GridLayout(1, 2));
		moneyPanel.setBackground(Color.WHITE);
		p1phrase = new JLabel("Player 1 has " + player1Money);
		p2phrase = new JLabel("Player 2 has " + player2Money);
		moneyPanelP1.add(p1phrase);
		moneyPanelP2.add(p2phrase);
		moneyPanelP1.setBackground(Color.WHITE);
		moneyPanelP2.setBackground(Color.WHITE);
		moneyPanel.add(moneyPanelP1);
		moneyPanel.add(moneyPanelP2);

		moneyPanelP1.setMaximumSize(new Dimension(100, 100));
		moneyPanelP2.setMaximumSize(new Dimension(100, 100));
		getContentPane().add(moneyPanel, BorderLayout.NORTH);

		int comboBoxCount = 0;
		for (int row = 0; row < field.getDepth(); row++) {
			for (int col = 0; col < field.getWidth(); col++) {
				Object character = field.getObjectAt(row, col);
				GridBagConstraints c = new GridBagConstraints();
				getConstraints(c, row, col);
				if (row == 0 || row == field.getDepth() - 1) {
					board.add((JComboBox<Character>) (comboBoxes.keySet().toArray())[comboBoxCount], c);
					comboBoxCount++;
				} else {
					board.add(makeJLabelPic(EMPTY_SPACE_FILE), c);
					// System.out.println("Row: " + c.gridx + " Col: " +
					// c.gridy);
				}
			}
		}

		board.updateUI();
	}

	private class DropDownListens implements ActionListener {
		private int previousCost = 0;
		private String player = "";

		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent ev) {
			try{
			if (((JComboBox<Character>) ev.getSource()).getSelectedItem() != null) {
				Character character = (Character) ((JComboBox<Character>) ev.getSource()).getSelectedItem();
				character.setCurrentLocation(comboBoxes.get(ev.getSource()));
				if (character.getPlayer().equals("Player 1")) {
					player1Money += previousCost;
					if (character.getCost() <= player1Money) {
						field.place(((JComboBox<Character>) ev.getSource()).getSelectedItem(),
								comboBoxes.get(ev.getSource()));
						player1Money -= character.getCost();
						previousCost = character.getCost();
						p1phrase.setText("Player 1 has " + player1Money);
						player = "Player 1";
					} else {
						previousCost = 0;
						((JComboBox<Character>) ev.getSource()).setSelectedItem(null);
						
						p1phrase.setText("Player 1 has " + player1Money);
						field.clear(comboBoxes.get(ev.getSource()));
						throw new NotEnoughMoneyException("Player 1", player1Money);
						
					}
				} else if (character.getPlayer().equals("Player 2")) {
					player2Money += previousCost;
					if (character.getPlayer().equals("Player 2") && character.getCost() <= player2Money) {
						field.place(((JComboBox<Character>) ev.getSource()).getSelectedItem(),
								comboBoxes.get(ev.getSource()));

						player2Money -= character.getCost();
						previousCost = character.getCost();
						p2phrase.setText("Player 2 has " + player2Money);
						player = "Player 2";
					} else {
						previousCost = 0;
						((JComboBox<Character>) ev.getSource()).setSelectedItem(null);
						p2phrase.setText("Player 2 has " + player2Money);
						field.clear(comboBoxes.get(ev.getSource()));
						throw new NotEnoughMoneyException("Player 2", player2Money);
					}
				}
			} else {
				field.clear(comboBoxes.get(ev.getSource()));
				if (player.equals("Player 1")) {
					player1Money += previousCost;
					p1phrase.setText("Player 1 has " + player1Money);
				} else if (player.equals("Player 2")) {
					player2Money += previousCost;
					p2phrase.setText("Player 2 has " + player2Money);
				}
				previousCost = 0;
			}
			}catch(NotEnoughMoneyException e){
				e.displayMessage();
			}
			moneyPanelP1.updateUI();
			moneyPanelP2.updateUI();
		}
	}

	private class nextTurnListens implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			showStatus(step);
		}
	}

	private class CharacterListens implements ActionListener {

		// @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent ev) {
			setBoard();
			Character character = ((JReferencingButton<Character>) ev.getSource()).getValue();
			String[] values = { "Move", "Attack", "Heal" };
			Object selected = new Object();
			if (!(character instanceof Healable)) {
				selected = JOptionPane.showInputDialog(null,
						character + "\n HP: " + character.getHP() + "/" + character.getMaxHP() + "\n DMG: "
								+ character.getDamage() + "\nWhat do you want this character to do?",
						"Character Options", JOptionPane.DEFAULT_OPTION, null, values, "0");
			} else {
				selected = JOptionPane.showInputDialog(null,
						character + "\n HP: " + character.getHP() + "/" + character.getMaxHP() + "\n DMG: "
								+ character.getDamage() + "\n Heal amount: " + (character.getHealAmount() * -1)
								+ "\nWhat do you want this character to do?",
						"Character Options", JOptionPane.DEFAULT_OPTION, null, values, "0");
			}
			if (selected != null) {// null if the user cancels.
				String selectedString = selected.toString();
				if (selectedString.equals("Move")) {
					if (character.getReadyToMove()) {
						for (Location location : field.getMoveLocations(character.getCurrentLocation(),
								character.getMoveRange())) {
							GridBagConstraints c = new GridBagConstraints();
							getConstraints(c, location.getRow(), location.getCol());
							if (field.getObjectAt(location) == null) {
								board.remove((Component) fieldLayout[c.gridx][c.gridy]);
								JReferencingButtonTwo<Character, Location> buttonToAdd = makeMoveSpace(character,
										location);
								board.add(buttonToAdd, c);
							}
						}
					} else
						JOptionPane.showMessageDialog(null, "This character cannot move again!");
				} else if (selectedString.equals("Attack")) {
					for (Location location : field.getAttackLocations(character.getCurrentLocation(),
							character.getAttackRange())) {
						if (((Character) field.getObjectAt(location)) != null
								&& (!((Character) field.getObjectAt(location)).getPlayer()
										.equals(character.getPlayer()))) {
							Character enemy = ((Character) field.getObjectAt(location));
							GridBagConstraints c = new GridBagConstraints();
							getConstraints(c, location.getRow(), location.getCol());
							board.remove((Component) fieldLayout[c.gridx][c.gridy]);
							JReferencingButtonTwo<Character, Character> buttonToAdd = makeActionSpace(character, enemy,
									false);
							fieldLayout[c.gridx][c.gridy] = buttonToAdd;
							board.add(buttonToAdd, c);
						} else {
							GridBagConstraints c = new GridBagConstraints();
							getConstraints(c, location.getRow(), location.getCol());
							if (field.getObjectAt(location) == null) {
								board.remove((Component) fieldLayout[c.gridx][c.gridy]);
								JLabel labelToAdd = makeJLabelPic(ATTACK_SPACE_FILE);
								board.add(labelToAdd, c);
							}
						}
					}
				} else if (selectedString.equals("Heal")) {
					if (character instanceof Healable) {
						for (Location location : field.getAttackLocations(character.getCurrentLocation(),
								character.getAttackRange())) {
							if (((Character) field.getObjectAt(location)) != null
									&& (((Character) field.getObjectAt(location)).getPlayer()
											.equals(character.getPlayer()))) {
								Character ally = ((Character) field.getObjectAt(location));
								GridBagConstraints c = new GridBagConstraints();
								getConstraints(c, location.getRow(), location.getCol());
								board.remove((Component) fieldLayout[c.gridx][c.gridy]);
								JReferencingButtonTwo<Character, Character> buttonToAdd = makeActionSpace(character,
										ally, true);
								fieldLayout[c.gridx][c.gridy] = buttonToAdd;
								board.add(buttonToAdd, c);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "This character cannot heal!");
					}
				} else {
				}
				board.updateUI();
			}
		}
	}

	public class MoveButtonListens implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			@SuppressWarnings("unchecked")
			JReferencingButtonTwo<Character, Location> button = (JReferencingButtonTwo<Character, Location>) ev
					.getSource();
			Location location = (Location) button.getValue2();
			Character character = (Character) button.getValue();
			character.setLocation(location);

			character.setReadyToMove(false);
			showStatus(step);

		}

	}

	public class AttackButtonListens implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			@SuppressWarnings("unchecked")
			JReferencingButtonTwo<Character, Character> button = (JReferencingButtonTwo<Character, Character>) ev
					.getSource();

			Character characterAtt = (Character) button.getValue();
			Character characterHit = (Character) button.getValue2();

			String fileName1 = "";
			String fileName2 = "";

			if (characterAtt.getPlayer().equals("Player 1")) {
				fileName1 = characterAtt.toString() + "P1.gif";
				fileName2 = characterHit.toString() + "P2_Hit.gif";
			} else {
				fileName1 = characterHit.toString() + "P1_Hit.gif";
				fileName2 = characterAtt.toString() + "P2.gif";
			}

			AnimationGUI animation = new AnimationGUI(fileName1, fileName2);

			characterAtt.attack(characterHit);
			characterAtt.setReadyToAttack(false);

			animation.close();

			if (characterHit.getHP() <= 0) {
				field.clear(((Character) button.getValue2()).getCurrentLocation());
			}
			if (characterAtt.getHP() <= 0) {
				field.clear(((Character) button.getValue()).getCurrentLocation());
			}

			showStatus(step);
		}

	}

	public class HealButtonListens implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			@SuppressWarnings("unchecked")
			JReferencingButtonTwo<Character, Character> button = (JReferencingButtonTwo<Character, Character>) ev
					.getSource();
			((Healable) button.getValue()).heal((Character) button.getValue2());
			((Character) button.getValue()).setReadyToAttack(false);
			showStatus(step);
		}

	}

	public class EnemyListens implements ActionListener {

		public void actionPerformed(ActionEvent ev) {

			Character character = ((JReferencingButton<Character>) ev.getSource()).getValue();
			for (Location location : field.getMoveLocations(character.getCurrentLocation(),
					(character.getMoveRange() + character.getAttackRange()))) {

				if (field.getObjectAt(location) == null) {
					GridBagConstraints c = new GridBagConstraints();
					getConstraints(c, location.getRow(), location.getCol());
					board.remove((Component) fieldLayout[c.gridx][c.gridy]);
					JLabel labelToAdd = makeJLabelPic(ATTACK_SPACE_FILE);
					fieldLayout[c.gridx][c.gridy] = labelToAdd;
					board.add(labelToAdd, c);

				}
			}

			board.updateUI();
		}

	}

	public void addActionListener() {
		nextTurn.addActionListener(new nextTurnListens());
	}

	/**
	 * Places the winner gif on the screen.
	 * 
	 * @throws MalformedURLException
	 */
	public void placeGif() throws MalformedURLException {
		try {

			URL gifURL = new File(P1_WIN).toURI().toURL();
			Icon icon = new ImageIcon(gifURL);
			p1WinPanel = new JLabel(icon);

			URL gifURL2 = new File(P2_WIN).toURI().toURL();
			Icon icon2 = new ImageIcon(gifURL2);
			p2WinPanel = new JLabel(icon2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays the winner.
	 * 
	 * @param player1Win
	 *            Shows who won the game.
	 */
	public void win(boolean player1Win) {
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			placeGif();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().removeAll();
		if (player1Win) {
			getContentPane().add(p1WinPanel, BorderLayout.CENTER);
		}

		else {
			getContentPane().add(p2WinPanel, BorderLayout.CENTER);
		}

		this.repaint();
		this.setMinimumSize(new Dimension(700, 500));
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		gui.getStartFrame().setVisible(true);
		this.setVisible(true);
	}
}