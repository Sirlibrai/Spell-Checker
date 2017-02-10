import java.util.Iterator;

public class HashTable implements Iterator<String> {

	private String[] table;
	private HashCode hashCode;
	private float loadFactor;
	private int load;
	private int probes;
	private int currentIndex;
	
	public HashTable() {			
	}
	public HashTable(int initialCapacity, float loadFactor, HashCode sH) {
		
		int prime = findNextPrime(initialCapacity);
		table = new String[prime];
		this.loadFactor = loadFactor;
		hashCode = sH;
		load = 0;
	}

	public void insert(String value) {
		int hValue = hashCode.giveCode(value);
		
		int count = 0;	
		float currentLoadfactor = (float) load / (float) size();
		if (currentLoadfactor >= loadFactor) {
			rehash();
		}
		
		boolean valid=false;
				
		int hash = Math.abs(compress(hValue));
		int compressed = hash;
		while (!valid) {
			if (table[hash] != null) {
				count++;
				hash = compressAgain(hValue, compressed, count);
			} else {
				valid=true;
			}
		}		
		table[hash] = value;
		load++;
	}
	
	public int compress(int hash) {
		int x = 173;
		int z = 7;
		int compress = Math.abs(((x * hash) + z) % size());
	
		return compress;
	}
	
	public int compressAgain(int v, int hash, int m) {
		
		probes++;
		int y = findNextPrime(size()/2);
		int rehash = y - (v % y);	
		int compress =  (hash + (m * rehash)) % size(); 
		return Math.abs(compress);
	}
	
	public void remove(String value) throws DictionaryException {
		int hash = getDoubleHash(value);
		
		if (hash > -1) {
			table[hash] = "";
			load--;
		} else {
			throw new DictionaryException();
		}
	}
	
	public boolean contains(String value) {
		if (getDoubleHash(value) > -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private int getDoubleHash(String value) {
		int hValue = hashCode.giveCode(value);
		
		int count=1;
		boolean found = false;
		int hash = compress(hValue);
		int compressed = hash;

		
		while (!found && count < size()) {
			if (table[hash] == null){
				break;
			} 
			
			if (!table[hash].equals(value)){
				hash = compressAgain(hValue, compressed, count);
				count++;
			} else {
				found=true;
			}
		}
		
		if (found) {
			return hash;
		} else {
			return -1;
		}
	}
	
	public void rehash() {
		int prime = findNextPrime(size() * 2);

		String[] Table = table;
		
		table = new String[prime];
		
		for (int i=0; i < Table.length; i++) {
			if (Table[i] != null) {
				if (!Table[i].equals("")) {
					insert(Table[i]);
				}
			}
		}
	}
	
	public int size() {
		return table.length;
	}

	private int findNextPrime(int X) {
		
		int test = 2;
		boolean prime=false;
		
				
		while (!prime) {
			prime = true;

			while (test * test <= X ) {
				if (X % test == 0) {
					prime = false;
				}
				test++;
			}			
			
			if (!prime) {
				X++;
			}
		}
		return X;
	}

	public boolean isEmpty() {
		if (size() != 0 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasNext() {
		int tempIndex = currentIndex;
		
		while (table[tempIndex] == null || table[tempIndex].equals("")) {
			if (tempIndex + 1 < size()) {
				
				tempIndex++;
			} else {
				break; 
			}
		}
		if (tempIndex + 1 < size()) {
			return true;
		} else {
			return false;
		}
	}
	public String next() {
		
		while (table[currentIndex] == null || table[currentIndex].equals("")) {		
			currentIndex++;
		}
		String Z = table[currentIndex];
		currentIndex++;	
		return Z;
	}

	public void remove() {
		table[currentIndex]	= "";	
	}	
	public int getProbes() {
		return probes;
	}
}