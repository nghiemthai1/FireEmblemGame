import java.time.LocalDate;
import java.time.LocalTime;

public class NotEnoughMoneyException extends RuntimeException{
 LocalDate date = LocalDate.now();
 LocalTime time = LocalTime.now();
 
 public NotEnoughMoneyException(){
  System.err.println("Get it together. Mistake made at " + time + "," + date);
 }
 
 
}