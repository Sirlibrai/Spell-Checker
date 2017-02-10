import java.util.LinkedList;
import java.util.Iterator;

public class ListDictionary implements Dictionary {

	private LinkedList<String> term;
	public ListDictionary() {
		term = new LinkedList<String>();
	}

	public void insert(String key) throws DictionaryException {
		if (!this.find(key) ) {
			term.add(key);
		} else {
			throw new DictionaryException();
		}
			
	}
	public void remove(String key) throws DictionaryException {
		if (!this.find(key) ) {
			term.add(key);
		} else {
			throw new DictionaryException();
		}
	}

	public boolean find(String key) {
		return term.contains(key);
		
	}
	public Iterator<String> elements() {
		return term.iterator();
	}

}
