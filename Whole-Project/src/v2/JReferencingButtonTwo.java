import javax.swing.Icon;
import javax.swing.JButton;

public class JReferencingButtonTwo<T,S> extends JButton 
{

private T value;
private S value2;
  
protected JReferencingButtonTwo(Icon icon, T value, S value2) {
	super(icon);
	this.value = value;
	this.value2 = value2;
}

public T getValue() 
  {
    return this.value;
  }

public S getValue2() 
{
  return this.value2;
}

  public void setValue(T value) 
  {
    this.value = value;
  }
}