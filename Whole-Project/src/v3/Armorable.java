package v3;

/**
 * 
 * @author Austin Huang
 * This class is used to implement armor or damage reduction involving a base armor reduction.
 */
public interface Armorable {
 
 static final double dmgTaken = 0.9;
 
 public static double getdmgTaken(){
  return dmgTaken;
 }
}