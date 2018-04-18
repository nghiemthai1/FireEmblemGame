package v3;

import java.util.Random;

import javax.swing.JOptionPane;

public class Assassin extends Melee implements Critable,Avoidable{
 public static final int maxHP = 200;
 public static final int maxAttackRange = 1;
 public static final int maxMovement = 5;
 public static final int maxDamage = 250;
 public static final int bonusCrit = 35;
 public static final int criChance = bonusCrit + Critable.getBaseCrit();
 public static final int criDMG = 2;
 public static final int bonusAvoid = 15;
 public static final int avoidability = bonusAvoid + Avoidable.getBaseAvoidability();
 private int cost = 4;
  
  public Assassin(int row, int col,String player, Field field, String fileName) {
   super(maxHP, maxAttackRange, maxMovement, maxDamage,row, col, player, field, fileName);
  }
  
  /**
   * Attack method using criChance and criDMG to add critical hit effects.
   */
  public void attack(Character character){
   int rand = new Random().nextInt(100)+1;
   int pastHP = character.getHP();
   if((criChance >= rand)){
    character.takeDamage(criDMG*this.damage);
    if(character.getHP() <= 0) {
      character.setAlive(false);
      JOptionPane.showMessageDialog(null," " + character + " died to a critical hit.");
    }
    else{
    if (character.getHP() != pastHP){
     JOptionPane.showMessageDialog(null," " + this + " attacked " + character + ". It was a "
          
      + "critial hit!");
    }
     
    } 
   }
   else{
    super.attack(character);
   }
  }
  
  /**
   * Take damage method that implements avoid chance.
   */
  public void takeDamage(int damage){
   int rand = new Random().nextInt(100)+1;
   if(avoidability >= rand ){
    JOptionPane.showMessageDialog(null, " " + this + " avoided the attack."); //Change order of JOptionPanes
   }
   else{
    super.takeDamage(damage);
   }
  }
  
  

 @Override
 public String toString() {
  // TODO Auto-generated method stub
  return "Assassin";
 }
 
 public int getCost(){
  return cost;
 }
 
 /**
  * Gets list of stats, also calls super getStats() method.
  */
 public String getStats(){
  String stat = super.getStats();
  stat = stat + "\n CRITICAL CHANCE: " + criChance + "%" + "\n CRITICAL DMG MULTIPLIER: " + criDMG  + "\n AVOID CHANCE: "
    + avoidability + "%" + "\n COST: " + cost;
  return stat;
 }
 
}