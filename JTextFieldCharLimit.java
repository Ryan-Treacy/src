import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class JTextFieldCharLimit extends PlainDocument{

	private static final long serialVersionUID = -6193260351538523849L;
	
	private int limit = 140;
	
	public JTextFieldCharLimit(int limit){
		this.limit = limit;
	}
	
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException{
		if(str == null){
			return;
		}else if(getLength() +str.length() <= limit){
			super.insertString(offset, str, set);
		}
	}
}


