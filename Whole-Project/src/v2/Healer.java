import javax.swing.JOptionPane;

public class Healer extends Range implements Healable {
	static final int maxHP = 100;
	static final int maxAttackRange = 2;
	static final int maxMovement = 5;
	static final int maxDamage = 0;
	static final int bonusHeal = -100;
	int cost = 1;
	static final int  healAmount= Healable.getBaseHeal() + bonusHeal;

	public Healer(int row, int col, String player, Field field, String fileName) {
		super(maxHP, maxAttackRange, maxMovement, maxDamage, row, col, player, field, fileName);
	}
	
	/**
	 * Heal method based off of passing in negative number to take damage method.
	 */
	public void heal(Character character){
	    character.takeDamage(Healable.getBaseHeal() + bonusHeal);
	    
	    if(character.getHP() > character.getMaxHP()){
	     character.setHP(character.maxHP);
	    }
	    
	    JOptionPane.showMessageDialog(null," " + this + " healed " + character + " for " + (this.getHealAmount()*(-1)) + ". " + character
	            + " has " + character.getHP() + " HP.");
	  }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Healer";
	}

	public int getCost() {
		return cost;
	}
	
	public int getHealAmount(){
		return healAmount;
	}
	
	/**
	Gets list of stats, also calls super getStats() method.
	*/
	 public String getStats(){
		  String stat = super.getStats();
		  stat = stat + "\n HEAL RATE: " + healAmount + "\n COST: " + cost;
		  return stat;
		 }
	

}