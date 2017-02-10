
import java.util.Iterator;

public class HashDictionary implements Dictionary {

	private HashTable dict;
	private int averNumOps;
	
	//calls Dictionary Exception
	public HashDictionary() throws DictionaryException{
		throw new DictionaryException();
	};
	
	public HashDictionary(StringHashCode sH, Float sF) {
		dict = new HashTable(7, sF, sH);
		averNumOps = 0;
	}
	
	public void insert(String key) throws DictionaryException {
		if (!dict.contains(key)) {
			dict.insert(key);			
		} else {
			throw new DictionaryException();
		}
		averNumOps++;
	}

	public void remove(String key) throws DictionaryException {
		dict.remove(key);
		averNumOps++;
	}

	public boolean find(String key) {
		
		averNumOps++;
		
		if (dict.contains(key)) {	
			return true;
		} else {
			return false;
		}
		
	}
	public Iterator<String> elements() {
		Iterator<String> it = dict;
		
		return it;
	}
	public float averNumProbes() {
		float averProbes = (float)dict.getProbes() / (float)averNumOps;
		return averProbes;
	}
	
}