package v3;

public class DragonKnight extends Melee{

 public static final int maxHP = 400;
 public static final int maxAttackRange = 1;
 public static final int maxMovement = 6;
 public static final int maxDamage = 200;
 private int cost = 5;
 
 /**
  * Constructor
  * @param row
  * @param col
  * @param player
  * @param field
  * @param fileName
  */
 public DragonKnight(int row, int col, String player, Field field, String fileName) {
  super(maxHP, maxAttackRange, maxMovement, maxDamage, row, col, player, field, fileName);
  // TODO Auto-generated constructor stub
 }

 public String toString(){
  return "Dragon Knight";
 }
 
 public int getCost(){
  return cost;
 }
 
 public String getStats(){
  String stat = super.getStats();
  return stat;
 }
}