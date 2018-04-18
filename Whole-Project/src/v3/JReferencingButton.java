package v3;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * A custom button that holds a specified value.
 * @author Matthew Rodriguez
 *
 * @param <T> The type of object this class will hold.
 */
public class JReferencingButton<T> extends JButton 
{

private T value;
  
/**
 * Constructs a button and gives it a specified value.
 * @param text Text that the button will display.
 * @param value The value that the button will hold.
 */
protected JReferencingButton(String text, T value) {
		super(text);
		this.value = value;
	}

/**
 * Constructs a button and gives it a specified value.
 * @param icon an icon the button will display.
 * @param value The value that the button will hold.
 */
protected JReferencingButton(Icon icon, T value) {
	super(icon);
	this.value = value;
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
 * Sets the value the button will hold.
 * @param value The value that the button will hold.
 */
  public void setValue(T value) 
  {
    this.value = value;
  }
}