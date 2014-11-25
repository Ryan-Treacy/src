import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class JTextFieldCharLimit extends PlainDocument{

	private static final long serialVersionUID = -6193260351538523849L;
	
	// Amount of characters that can be typed.
	private int limit = 140;
	
	// Setter
	public JTextFieldCharLimit(int limit){
		this.limit = limit;
	}
	
	// Checks to make sure the user post is not to long.  If limit is reached it prevents more characters from being 
	//		added to the text field.
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException{
		if(str == null){
			return;
		}else if(getLength() +str.length() <= limit){
			super.insertString(offset, str, set);
		}
	}
}


