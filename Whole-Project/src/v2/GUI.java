import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame {
    private static final String P1_ARCHER = "Player_1_Archer.jpg";
    private static final String P2_ARCHER = "Player_2_Archer.jpg";
    private static final String P1_KNIGHT = "Player_1_Knight.jpg";
    private static final String P2_KNIGHT = "Player_2_Knight.jpg";
    private static final String P1_WARRIOR = "Player_1_Warrior.jpg";
    private static final String P2_WARRIOR = "Player_2_Warrior.jpg";
    private static final String P1_MAGE = "Player_1_Mage.jpg";
    private static final String P2_MAGE = "Player_2_Mage.jpg";
    private static final String P1_HEALER = "Player_1_Priest.jpg";
    private static final String P2_HEALER = "Player_2_Priest.jpg";
    private static final String P1_DRAGONKNIGHT = "Player_1_DK.jpg";
    private static final String P2_DRAGONKNIGHT = "Player_2_DK.jpg";
    private static final String P1_ALCHEMIST = "Player_1_Alchemist.jpg";
    private static final String P2_ALCHEMIST = "Player_2_Alchemist.jpg";
    private static final String P1_ASSASSIN = "Player_1_Assassin.jpg";
    private static final String P2_ASSASSIN = "Player_2_Assassin.jpg";
    
    private static final String P1_WIN = "BlueTeamVictory.gif";
    private static final String P2_WIN = "RedTeamVictory.gif";
    
    private static final int STARTING_CHARS = 18;
    private static final int NUMBER_OF_CLASSES = 9;
    private static final int SIZE_OF_COMBOBOX = 400;
    private static final String EMPTY_SPACE_FILE = "Grass.jpg";
    private static final String MOVE_SPACE_FILE = "BlueGrass.jpg";
    private static final String ATTACK_SPACE_FILE = "RedGrass.jpg";
    private static final int PIC_WIDTH = 150;
    private static final int PIC_HEIGHT = 65;
    private static final int STARTING_MONEY = 15;
    private static final String PLAYER1_PIC = "Player1.jpg";
    private static final String PLAYER2_PIC = "player2.jpg";
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 300;
    private final String TURN_LABEL = "Turns: ";

    private JLabel stepLabel, population;
    private JPanel board = new JPanel();
    private JLabel turnLabel = new JLabel();
    private JLabel actualTurn = new JLabel();
    private JTextField dialog;
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

    // A map for storing colors for participants in the simulation
    private Map<Character, Image> images;
    // A statistics object computing and storing simulation information
    // private FieldStats stats;

    /**
     * Create a view of the given width and height.
     * 
     * @param height
     *            The simulation's height.
     * @param width
     *            The simulation's width.
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

        setTitle("Fire Emblem");
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
        this.setSize(1910, 900);
        this.setMinimumSize(new Dimension(900, 700));
        this.setLocationRelativeTo(null);
        moneyPanel = new JPanel();
        moneyPanelP1 = new JPanel();
        moneyPanelP2 = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }

    public void populateArray() {

        for (int col = 0; col < height; col += (height - 1)) {
            for (int row = 0; row < width; row++) {
                characters = new Character[NUMBER_OF_CLASSES];
                if (col == 0) {
                    String player1 = "Player 1";
                    characters[0] = null;
                    characters[1] = new Warrior(row, col, player1, this.field, P1_WARRIOR);
                    setImage(characters[1]);
                    characters[2] = new Archer(row, col, player1, this.field, P1_ARCHER);
                    setImage(characters[2]);
                    characters[3] = new Mage(row, col, player1, this.field, P1_MAGE);
                    setImage(characters[3]);
                    characters[4] = new DragonKnight(row, col, player1, this.field, P1_DRAGONKNIGHT);
                    setImage(characters[4]);
                    characters[5] = new Healer(row, col, player1, this.field, P1_HEALER);
                    setImage(characters[5]);
                    characters[6] = new Knight(row, col, player1, this.field, P1_KNIGHT);
                    setImage(characters[6]);
                    characters[7] = new Alchemist(row, col, player1, this.field, P1_ALCHEMIST);
                    setImage(characters[7]);
                    characters[8] = new Assassin(row, col, player1, this.field, P1_ASSASSIN);
                    setImage(characters[8]);
                } else {
                    String player2 = "Player 2";
                    characters[0] = null;
                    characters[1] = new Warrior(row, col, player2, this.field, P2_WARRIOR);
                    setImage(characters[1]);
                    characters[2] = new Archer(row, col, player2, this.field, P2_ARCHER);
                    setImage(characters[2]);
                    characters[3] = new Mage(row, col, player2, this.field, P2_MAGE);
                    setImage(characters[3]);
                    characters[4] = new DragonKnight(row, col, player2, this.field, P2_DRAGONKNIGHT);
                    setImage(characters[4]);
                    characters[5] = new Healer(row, col, player2, this.field, P2_HEALER);
                    setImage(characters[5]);
                    characters[6] = new Knight(row, col, player2, this.field, P2_KNIGHT);
                    setImage(characters[6]);
                    characters[7] = new Alchemist(row, col, player2, this.field, P2_ALCHEMIST);
                    setImage(characters[7]);
                    characters[8] = new Assassin(row, col, player2, this.field, P2_ASSASSIN);
                    setImage(characters[8]);
                }
                JComboBox<Character> boxToAdd = new JComboBox<Character>(characters);
                boxToAdd.setMinimumSize(new Dimension(SIZE_OF_COMBOBOX, SIZE_OF_COMBOBOX));
                comboBoxes.put(boxToAdd, new Location(col, row));
                boxToAdd.addActionListener(new DropDownListens());
            }
        }
    }

    private JLabel makeEmptySpace() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(EMPTY_SPACE_FILE));
            JLabel emptySpace = new JLabel(new ImageIcon(image));
            emptySpace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            return emptySpace;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private JLabel makeAttackRangeSpace() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(ATTACK_SPACE_FILE));
            JLabel emptySpace = new JLabel(new ImageIcon(image));
            emptySpace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            return emptySpace;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private JReferencingButtonTwo<Character, Location> makeMoveSpace(Character character, Location location) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(MOVE_SPACE_FILE));
            JReferencingButtonTwo<Character, Location> picLabel = new JReferencingButtonTwo<Character, Location>(
                    new ImageIcon(image), character, location);
            picLabel.setMinimumSize(new Dimension(100, 100));
            picLabel.addActionListener(new MoveButtonListens());
            return picLabel;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private JReferencingButtonTwo<Character, Character> makeAttackSpace(Character character, Character enemy) {
        BufferedImage image;
        JReferencingButtonTwo<Character, Character> picLabel = new JReferencingButtonTwo<Character, Character>(
                new ImageIcon(images.get(enemy)), character, enemy);
        picLabel.setMinimumSize(new Dimension(100, 100));
        picLabel.addActionListener(new AttackButtonListens());
        return picLabel;
    }

    private JReferencingButtonTwo<Character, Character> makeAttackSpace(Character character, Character ally,
            boolean heal) {
        BufferedImage image;
        JReferencingButtonTwo<Character, Character> picLabel = new JReferencingButtonTwo<Character, Character>(
                new ImageIcon(images.get(ally)), character, ally);
        picLabel.setMinimumSize(new Dimension(100, 100));
        picLabel.addActionListener(new HealButtonListens());
        return picLabel;
    }

    public void setImage(Character character) {
        images.put(character, loadImage(character.getFileName()).getScaledInstance(140, 65, Image.SCALE_SMOOTH));
    }

    public BufferedImage loadImage(String fileName) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(fileName));
            return image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    private JReferencingButton<Character> getImage(Class characterClass, Character character) {
        JReferencingButton<Character> picLabel = new JReferencingButton<Character>(new ImageIcon(images.get(character)),
                character);
        picLabel.setMinimumSize(new Dimension(100, 100));
        picLabel.addActionListener(new CharacterListens());
        return picLabel;
    }

    private JLabel getImageLabel(Character character) {
        JLabel picLabel = new JLabel(new ImageIcon(images.get(character)));
        picLabel.setMinimumSize(new Dimension(100, 100));
        return picLabel;
    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        moneyPanelP2.setVisible(false);
        moneyPanelP1.setVisible(false);
    }

    public void showStatus(int turn, Field field) {
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
                c.gridx = row;
                c.gridy = col;
                c.ipadx = 0;
                c.ipady = 0;
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                if (character != null) {
                    if (((Character) character).getPlayer().equals(playerTurn)
                            && ((Character) character).getReadyToAttack()) {
                        JReferencingButton<Character> charToAdd = getImage(character.getClass(), (Character) character);
                        board.add(charToAdd, c);
                        fieldLayout[row][col] = charToAdd;
                        if(((Character) character).getPlayer().equals("Player 1")) player1Count++;
                        if(((Character) character).getPlayer().equals("Player 2")) player2Count++;
                    } else {
                        ((Character) character).setReadyToAttack(true);
                        ((Character) character).setReadyToMove(true);
                        JLabel charToAdd = getImageLabel((Character) character);
                        board.add(charToAdd, c);
                        fieldLayout[row][col] = charToAdd;
                        if(((Character) character).getPlayer().equals("Player 1")) player1Count++;
                        if(((Character) character).getPlayer().equals("Player 2")) player2Count++;
                    }
                } else {    
                    JLabel spaceToAdd = makeEmptySpace();
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
    public void populate(Field field) {
        board.removeAll();
        if (!isVisible()) {
            setVisible(true);
        }

        // turnLabel.setText(TURN_LABEL + turn);
        moneyPanel.setLayout(new GridLayout(1, 2));
        p1phrase = new JLabel("Player 1 has " + player1Money);
        p2phrase = new JLabel("Player 2 has " + player2Money);
        moneyPanelP1.add(p1phrase);
        moneyPanelP2.add(p2phrase);
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
                c.gridx = row;
                c.gridy = col;
                c.ipadx = 0;
                c.ipady = 0;
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                if (row == 0 || row == field.getDepth() - 1) {
                    board.add((JComboBox<Character>) (comboBoxes.keySet().toArray())[comboBoxCount], c);
                    comboBoxCount++;
                } else {
                    board.add(makeEmptySpace(), c);
                    // System.out.println("Row: " + c.gridx + " Col: " +
                    // c.gridy);
                }
            }
        }

        board.updateUI();
    }

    private class DropDownListens implements ActionListener {
        private int previousCost = 0;
        private String player;

        @SuppressWarnings("unchecked")
        public void actionPerformed(ActionEvent ev) {
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
                        ((JComboBox<Character>) ev.getSource()).setSelectedItem(null);
                        JOptionPane.showInputDialog(null,
                                "Not enough for this character. Player 1 has only " + player1Money
                                        + " left. \nNow think about your poor financial decisions and write down your thoughts. Please and thank you...",
                                "Character Options", JOptionPane.DEFAULT_OPTION);
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
                        ((JComboBox<Character>) ev.getSource()).setSelectedItem(null);
                        JOptionPane.showInputDialog(null,
                                "Not enough for this character. Player 1 has only " + player2Money
                                        + " left.\nNow think about your poor financial decisions and write down your thoughts. Please and thank you...",
                                "Character Options", JOptionPane.DEFAULT_OPTION);
                    }
                }
            } else {
                if (player.equals("Player 1")) {
                    player1Money += previousCost;
                    p1phrase.setText("Player 1 has " + player1Money);
                } else if (player.equals("Player 2")) {
                    player2Money += previousCost;
                    p2phrase.setText("Player 2 has " + player2Money);
                }
                previousCost = 0;
            }
        }
    }

    private class nextTurnListens implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            showStatus(step, field);
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
                            c.gridx = location.getRow();
                            c.gridy = location.getCol();
                            c.ipadx = 0;
                            c.ipady = 0;
                            c.weightx = 1.0;
                            c.weighty = 1.0;
                            c.fill = GridBagConstraints.BOTH;
                            if (field.getObjectAt(location) == null) {
                                board.remove((Component) fieldLayout[c.gridx][c.gridy]);
                                JReferencingButtonTwo<Character, Location> buttonToAdd = makeMoveSpace(character,
                                        location);
                                board.add(buttonToAdd, c);
                            }
                        }
                    }
                    else JOptionPane.showMessageDialog(null, "This character cannot heal!");
                } else if (selectedString.equals("Attack")) {
                    for (Location location : field.getAttackLocations(character.getCurrentLocation(),
                            character.getAttackRange())) {
                        if (((Character) field.getObjectAt(location)) != null
                                && (!((Character) field.getObjectAt(location)).getPlayer()
                                        .equals(character.getPlayer()))) {
                            Character enemy = ((Character) field.getObjectAt(location));
                            GridBagConstraints c = new GridBagConstraints();
                            c.gridx = location.getRow();
                            c.gridy = location.getCol();
                            c.ipadx = 0;
                            c.ipady = 0;
                            c.weightx = 1.0;
                            c.weighty = 1.0;
                            c.fill = GridBagConstraints.BOTH;
                            board.remove((Component) fieldLayout[c.gridx][c.gridy]);
                            JReferencingButtonTwo<Character, Character> buttonToAdd = makeAttackSpace(character, enemy);
                            fieldLayout[c.gridx][c.gridy] = buttonToAdd;
                            board.add(buttonToAdd, c);
                        } else {
                            GridBagConstraints c = new GridBagConstraints();
                            c.gridx = location.getRow();
                            c.gridy = location.getCol();
                            c.ipadx = 0;
                            c.ipady = 0;
                            c.weightx = 1.0;
                            c.weighty = 1.0;
                            c.fill = GridBagConstraints.BOTH;
                            if (field.getObjectAt(location) == null) {
                                board.remove((Component) fieldLayout[c.gridx][c.gridy]);
                                JLabel labelToAdd = makeAttackRangeSpace();
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
                                c.gridx = location.getRow();
                                c.gridy = location.getCol();
                                c.ipadx = 0;
                                c.ipady = 0;
                                c.weightx = 1.0;
                                c.weighty = 1.0;
                                c.fill = GridBagConstraints.BOTH;
                                board.remove((Component) fieldLayout[c.gridx][c.gridy]);
                                JReferencingButtonTwo<Character, Character> buttonToAdd = makeAttackSpace(character,
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
            showStatus(step, field);

        }

    }

    public class AttackButtonListens implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            @SuppressWarnings("unchecked")
            JReferencingButtonTwo<Character, Character> button = (JReferencingButtonTwo<Character, Character>) ev
                    .getSource();
            ((Character) button.getValue()).attack((Character) button.getValue2());
            ((Character) button.getValue()).setReadyToAttack(false);


            if (((Character) button.getValue2()).getHP() <= 0) {
                field.clear(((Character) button.getValue2()).getCurrentLocation());
            }
            if(((Character) button.getValue()).getHP() <= 0){
                field.clear(((Character) button.getValue()).getCurrentLocation());
            }

            showStatus(step, field);
        }

    }

    public class HealButtonListens implements ActionListener {

        public void actionPerformed(ActionEvent ev) {
            @SuppressWarnings("unchecked")
            JReferencingButtonTwo<Character, Character> button = (JReferencingButtonTwo<Character, Character>) ev
                    .getSource();
            ((Healable) button.getValue()).heal((Character) button.getValue2());
            ((Character) button.getValue()).setReadyToAttack(false);
            showStatus(step, field);
        }

    }

    public void addActionListener() {
        nextTurn.addActionListener(new nextTurnListens());
    }
    
    public void placeGif()throws MalformedURLException{
          try{
           
           URL gifURL = new File(P1_WIN).toURI().toURL();
           Icon icon = new ImageIcon(gifURL);
           p1WinPanel = new JLabel(icon);
           
           URL gifURL2 = new File(P2_WIN).toURI().toURL();
           Icon icon2 = new ImageIcon(gifURL2);
           p2WinPanel = new JLabel(icon2);
          }
          catch(Exception e){
           e.printStackTrace();
          }
         }
    

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
        if (player1Win){
            getContentPane().add(p1WinPanel, BorderLayout.CENTER);
        }

        else{
            getContentPane().add(p2WinPanel, BorderLayout.CENTER);
        }
        
        this.repaint();
        this.setMinimumSize(new Dimension(700, 500));
        this.setSize(700,500);
        
        gui.getStartFrame().setVisible(true);
        this.setVisible(true);
    }
}