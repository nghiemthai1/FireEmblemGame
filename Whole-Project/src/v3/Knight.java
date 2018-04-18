package v3;
public class Knight extends Range implements Armorable{
	 
	 public static final int maxHP = 550;
	 public static final int maxAttackRange = 2;
	 public static final int maxMovement = 3;
	 public static final int maxDamage = 150;
	 public static final double bonusArmor = 0 ;
	 public static final double armor = bonusArmor + Armorable.getdmgTaken();
	 private int cost = 2;
	 
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