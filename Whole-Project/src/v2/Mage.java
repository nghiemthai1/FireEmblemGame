import javax.swing.JOptionPane;


public class Mage extends Range implements Healable{
 
 static final int maxHP = 150;
 static final int maxAttackRange = 2;
 static final int maxMovement = 3;
 static final int maxDamage = 300;
 static final int baseHeal = 0;
 static final int healAmount = baseHeal + Healable.getBaseHeal();
 int cost = 2;
 
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