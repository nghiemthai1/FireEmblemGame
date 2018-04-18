import javax.swing.Icon;
import javax.swing.JButton;

public class JReferencingButton<T> extends JButton 
{

private T value;
  
protected JReferencingButton(String text, T value) {
		super(text);
		this.value = value;
	}


protected JReferencingButton(Icon icon, T value) {
	super(icon);
	this.value = value;
}

public T getValue() 
  {
    return this.value;
  }

  public void setValue(T value) 
  {
    this.value = value;
  }
}