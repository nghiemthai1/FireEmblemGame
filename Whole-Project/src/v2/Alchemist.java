import javax.swing.JOptionPane;

public class Alchemist extends Melee{
 static final int maxHP = 100;
  static final int maxAttackRange = 1;
  static final int maxMovement = 4;
  static final int maxDamage = 400;
  int cost = 1;
  
 public Alchemist(int row, int col, String player, Field field, String fileName) {
  super(maxHP, maxAttackRange, maxMovement, maxDamage, row, col, player, field, fileName);
  // TODO Auto-generated constructor stub
 }
 
 public void attack(){
  
 }
 
 /**
  * Attacks and kills himself in the process.
  */
 public void attack (Character character){
    character.takeDamage(this.damage);
    if(character.getHP() <= 0) {
     character.setAlive(false);
     JOptionPane.showMessageDialog(null," " + character + " died."); 
    }
    else{
     JOptionPane.showMessageDialog(null, " " + this + " attacked " + character + ".");
     
    }
    goInt();
   }
  
  public void takeDamage (int damage){
   setHP(hp - damage);
  }
  
  /**
   * Method that deals damage to this unit as well.
   */
 public void goInt(){
  super.attack(this);
  this.setAlive(false);
 }
 

 @Override
 public String toString() {
  return "Alchemist";
 }
 
 public int getCost(){
	 return cost;
 }
 
 public String getStats(){
	  String stat = super.getStats();
	  return stat;
	 }
 
}