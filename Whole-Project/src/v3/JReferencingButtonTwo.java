package v3;
import javax.swing.Icon;
import javax.swing.JButton;
/**
 * A custom button that holds two specified value.
 * @author Matthew Rodriguez
 *
 * @param <T> The type of object this class will hold.
 * @param <S> The type of object this class will hold.
 */
public class JReferencingButtonTwo<T,S> extends JButton 
{

private T value;
private S value2;
  
/**
 * Constructs a button and gives it a specified value.
 * @param icon an icon the button will display.
 * @param value The value that the button will hold.
 * @param value2 The second value that the button will hold.
 */
protected JReferencingButtonTwo(Icon icon, T value, S value2) {
	super(icon);
	this.value = value;
	this.value2 = value2;
}

/**
 * Gives the value that the button is holding.
 * @return The value that the button is holding.
 */
public T getValue() 
  {
    return this.value;
  }

/**
 * Gives the value that the button is holding.
 * @return The value that the button is holding.
 */
public S getValue2() 
{
  return this.value2;
}

}