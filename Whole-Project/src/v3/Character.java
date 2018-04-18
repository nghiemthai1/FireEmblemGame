package v3;

import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * 
 * @author Austin Huang
 * Abstract super class for Character in order to make extension easier, also use of polymorphic method dispatch.
 */
public abstract class Character {
 protected int hp;
 protected int attackRange;
 protected int moveRange;
 protected int damage;
 protected int healAmount;
 protected int maxHP;

 protected String fileName;

 protected boolean isAlive;
 protected boolean readyToAttack;
 protected boolean readyToMove;
 protected String player;
 protected Field field;
 protected Location previousLocation;
 protected Location currentLocation;
/**
 * Constructor
 * @param hp
 * @param attackRange
 * @param moveRange
 * @param damage
 * @param row
 * @param col
 * @param player
 * @param field
 * @param fileName
 */
 public Character(int hp, int attackRange, int moveRange, int damage, int row, int col, String player, Field field,
   String fileName) {
  super();
  this.field = field;
  this.hp = hp;
  this.maxHP = hp;
  this.attackRange = attackRange;
  this.moveRange = moveRange;
  this.damage = damage;
  this.currentLocation = new Location(row, col);
  this.player = player;
  this.isAlive = true;
  this.readyToAttack = true;
  this.readyToMove = true;
  this.fileName = fileName;
 }

 // Getters and Setters

 public String getFileName() {
  return fileName;
 }
/**
 * Check if character is ready to attack.
 * @return
 */
 protected boolean getReadyToAttack() {
  return readyToAttack;
 }
/**
 * Set ready to attack.
 * @param readyToAttack
 */
 protected void setReadyToAttack(boolean readyToAttack) {
  this.readyToAttack = readyToAttack;
 }
/**
 * Check if character is ready to move.
 * @return
 */
 protected boolean getReadyToMove() {
  return readyToMove;
 }
 /**
  * Set ready to move.
  * @param readyToMove
  */
 protected void setReadyToMove(boolean readyToMove) {
  this.readyToMove = readyToMove;
 }

 protected String getPlayer() {
  return player;
 }

 protected void setPlayer(String player) {
  this.player = player;
 }

 public int getHP() {
  return hp;
 }

 public void setHP(int hp) {
  this.hp = hp;
 }

 public int getAttackRange() {
  return attackRange;
 }

 public void setAttackRange(int attackRange) {
  this.attackRange = attackRange;
 }

 public int getMoveRange() {
  return moveRange;
 }

 public void setMoveRange(int moveRange) {
  this.moveRange = moveRange;
 }

 public int getDamage() {
  return damage;
 }

 public void setDamage(int damage) {
  this.damage = damage;
 }

 public boolean isAlive() {
  return isAlive;
 }

 public void setAlive(boolean isAlive) {
  this.isAlive = isAlive;
 }

 public Field getField() {
  return field;
 }

 public void setField(Field field) {
  this.field = field;
 }

 public Location getPreviousLocation() {
  return previousLocation;
 }

 public void setPreviousLocation(Location previousLocation) {
  this.previousLocation = previousLocation;
 }

 public Location getCurrentLocation() {
  return currentLocation;
 }

 public void setCurrentLocation(Location currentLocation) {
  this.currentLocation = currentLocation;
 }

 // Methods beyond getters and setters
/**
 * Attack method for character using the takeDamage() method of passed in character.
 * @param character
 */
 public void attack (Character character){
  int pastHP = character.getHP();
     character.takeDamage(this.damage);
     if(character.getHP() <= 0) {
      character.setAlive(false);
      JOptionPane.showMessageDialog(null," " + character + " died.");  
     }
     else{
      if(character.getHP() != pastHP){
       JOptionPane.showMessageDialog(null," " + this + " attacked " + character + ". " + character
         + " has " + character.getHP() + " HP left.");
      }
     }
    }
/**
 * Allows character to take damage by passing in a damage amount.
 * @param damage
 */
 public void takeDamage(int damage) {
  setHP(hp - damage);
 }

 /*
  * public void move (Location moveLocation){ Location = this.getLocation();
  * 
  * }
  */
/**
 * Sets location of character.
 * @param location
 */
 public void setLocation(Location location) {
  previousLocation = currentLocation;
  //System.out.println(location.getRow());
  if (currentLocation != null) {
   field.clear(currentLocation);
  }
  currentLocation = location;
  field.place(this, location);

 }
/**
 * Moves characters back to previous location.
 */
 public void moveBack() {
  setLocation(previousLocation);
 }

 public abstract int getCost();

 public abstract String toString();

 /**
  * Super method for heal, will actually heal if sub class can heal.
  * @param character
  */
 public void heal(Character character) {
  System.out.println("I cant heal!");
 }

 
 public int getHealAmount() {
  return 0;
 }

 public int getMaxHP() {
  return maxHP;
 }

 public void setMaxHP(int maxHP) {
  this.maxHP = maxHP;
 }
 
 /**
  * Gets stats every character has. (super method)
  * @return
  */
 public String getStats(){
  String stats = "\n" + this + "\n HP: " + getMaxHP() + "\n DAMAGE:" + getDamage() + "\n MOVEMENT RANGE: " + getMoveRange()
  + "\n ATTACK RANGE: " + getAttackRange();
  return stats;
 }
 
// @SuppressWarnings("null")
//public ArrayList<Location> adjacentTiles(Location location){
//	int nextRow = location.getRow() + 1;
//	int nextCol = location.getCol() +1;
//	int preCol = location.getCol() - 1;
//	int preRow = location.getRow() -1;
//	ArrayList <Location> adjacentTiles = new ArrayList<>();
//	if (preCol > 0){
//		adjacentTiles.add(new Location(0,preCol));
//		if (preRow >0){
//			adjacentTiles.add(new Location(preRow,preCol));
//			if (nextRow <= GameClient.DEFAULT_WIDTH){
//				adjacentTiles.add(new Location(nextRow,preCol));
//				if (nextCol <= GameClient.DEFAULT_DEPTH){
//					adjacentTiles.add(new Location(nextRow,nextCol));
//					adjacentTiles.add(new Location(preRow,nextCol));
//				}
//			}
//		}
//	}
//	if (preRow>0){
//		adjacentTiles.add(new Location(preRow,0));
//	}
//	if (nextRow <= GameClient.DEFAULT_WIDTH){
//		adjacentTiles.add(new Location(nextRow,0));
//	}
//	if (nextCol <= GameClient.DEFAULT_DEPTH){
//		adjacentTiles.add(new Location(0,nextCol));
//	}
//	return adjacentTiles;
//	 
// }
 
}