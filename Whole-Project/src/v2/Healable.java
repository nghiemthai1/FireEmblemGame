/**
 * 
 * @author Austin Huang
 * Used to implement heals using base heal amount.
 */
public interface Healable {
	public static final int baseHeal = -100;
	
	public abstract void heal(Character character);
	
	static int getBaseHeal(){
		return baseHeal;
	}
	
}
