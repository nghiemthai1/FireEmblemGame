public class Knight extends Melee implements Armorable{
 
 static final int maxHP = 600;
 static final int maxAttackRange = 1;
 static final int maxMovement = 3;
 static final int maxDamage = 100;
 static final double bonusArmor = 0 ;
 static final double armor = bonusArmor + Armorable.getdmgTaken();
 int cost = 2;
 
 /**
  * Constructor
  * @param row
  * @param col
  * @param player
  * @param field
  * @param fileName
  */
 public Knight(int row, int col, String player, Field field,String fileName) {
  super(maxHP, maxAttackRange, maxMovement, maxDamage, row, col, player,  field, fileName);
  // TODO Auto-generated constructor stub
 }
 
 /**
  * Take damage implements damage reduction.
  */
  public void takeDamage (int damage){
   setHP((int)(hp - damage*(armor)));
  }
 
  public int getCost(){
   return cost;
  }
  
 public String toString(){
  return "Knight";
 }
 
 /**
  * Gets list of stats, also calls super getStats() method.
  */
 public String getStats(){
  String stat = super.getStats();
  stat = stat + "\n DMG TAKEN: " + armor  + "\n COST: " + cost;
  return stat;
 }
 
 
}