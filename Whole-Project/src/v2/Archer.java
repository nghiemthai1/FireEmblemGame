import java.util.Random;

import javax.swing.JOptionPane;

public class Archer extends Range implements Critable{

 static final int maxHP = 200;
 static final int maxAttackRange = 3;
 static final int maxMovement = 5;
 static final int maxDamage = 100;
 static final int criChance = 10;
 int cost = 2;
 static final int criDMG = 3;
 
 /*
  * Constructor
  */
 public Archer(int row, int col,String player, Field field, String fileName) {
  super(maxHP, maxAttackRange, maxMovement, maxDamage,row, col, player, field, fileName);
  // TODO Auto-generated constructor stub
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
	  
 
 public int getCost(){
	 return cost;
 }
 /**
  * toString for Archer
  */
 public String toString(){
	 return "Archer";
 }
 
 /**
  * Gets list of stats, also calls super getStats() method.
  */
 public String getStats(){
	  String stat = super.getStats();
	  stat = stat + "\n CRITICAL CHANCE: " + criChance + "%" + "\n CRITICAL DMG MULTIPLIER: " + criDMG + "\n COST: " + cost;
	  return stat;
	 }
}