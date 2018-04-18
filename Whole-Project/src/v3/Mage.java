package v3;
import javax.swing.JOptionPane;


public class Mage extends Range implements Healable{
 
 public static final int maxHP = 150;
 public static final int maxAttackRange = 2;
 public static final int maxMovement = 3;
 public static final int maxDamage = 300;
 public static final int baseHeal = 0;
 public static final int healAmount = baseHeal + Healable.getBaseHeal();
 private int cost = 2;
 
/**
 * Constructor
 * @param row
 * @param col
 * @param player
 * @param field
 * @param fileName
 */
 public Mage(int row, int col, String player,  Field field, String fileName) {
  super(maxHP, maxAttackRange, maxMovement, maxDamage, row, col, player, field, fileName);
 }
/**
 * Heal method based off of passing in negative number to take damage method.
 */
 public void heal(Character character){
     character.takeDamage(healAmount);
     
     if(character.getHP() > character.getMaxHP()){
      character.setHP(character.maxHP);
     }
     JOptionPane.showMessageDialog(null," " + this + " healed " + character + " for " + (this.getHealAmount()*(-1)) + ". " + character
             + " has " + character.getHP() + " HP.");
   }
 
 public int getCost(){
  return cost;
 }
 
 /*
  * (non-Javadoc)
  * @see Character#toString()
  */
 public String toString(){
  return "Mage";
 }
 
 
 public int getHealAmount(){
  return healAmount;
 }
 
 /**
  * Gets list of stats, also calls super getStats() method.
  */
 public String getStats(){
  String stat = super.getStats();
  stat = stat + "\n HEAL RATE: " + healAmount + "\n COST: " + cost;
  return stat;
 }
 
}