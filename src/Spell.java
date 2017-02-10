
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
 
public class Spell {
	
	public static HashDictionary dictionary;
	public Spell()
	{
		StringHashCode HC = new StringHashCode();
		dictionary = new HashDictionary(HC, (float) 0.75);	
	}
	
	public static LinkedList<String> LetterSubstitution(String word, LinkedList<String> words) {
		
		String newWord;
		char letter;	
		StringBuffer buffer = new StringBuffer(word); 
		
		//Replace every letter in the word with another letter to make a new word
		for (int i=0; i < word.length(); i++) {
			for (letter='a'; letter <= 'z'; letter++) {
				
				newWord = buffer.replace(i, i+1, String.valueOf(letter)).toString();
				
				if ((dictionary.find(newWord)) && (!words.contains(newWord))) {
						words.add(newWord);
					}
				buffer = new StringBuffer(word); //Resets buffer
			}
		}
			return words;
	}
	public static LinkedList<String> LetterOmission(String word, LinkedList<String> words) {
		
		String newWord="";
		
		StringBuffer buffer = new StringBuffer(word);
		for (int i = 0; i < word.length(); i++) {
			newWord = buffer.delete(i, i + 1).toString();
			
			if ((dictionary.find(newWord)) && (!words.contains(newWord))) {
					words.add(newWord);
			}
			buffer = new StringBuffer(word);
		}
		return words;
	}
	
	public static LinkedList<String> LetterReversal(String word, LinkedList<String> words) {
		
		String newWord= "";
		StringBuffer buffer = new StringBuffer(word);
		StringBuffer Swap = new StringBuffer();
		
		for (int i = 0; i < word.length()-1; i++) {
			Swap = new StringBuffer(buffer.substring(i, i + 2)).reverse();
			buffer.delete(i, i + 2);
			newWord = buffer.insert(i, Swap).toString();
			
			if ((dictionary.find(newWord)) && (!words.contains(newWord))) {
					words.add(newWord);
				}
			buffer = new StringBuffer(word);
		}
		return words;
		
	}
	
	public static LinkedList<String> LetterInsertion(String word, LinkedList<String> words) {
		
		String newWord = "";
		
		StringBuffer buffer = new StringBuffer(word);
		for (int i = 0; i < word.length(); i++) {
			for (char letter='a'; letter <= 'z'; letter++) {
				newWord = buffer.insert(i, letter).toString();
				
				if ((dictionary.find(newWord)) && (!words.contains(newWord))) {
						words.add(newWord);
					}
				buffer = new StringBuffer(word);
			}
		}
		return words;
	}
    public static void main(String[] args) throws java.io.IOException, DictionaryException{
    	 long Start = System.currentTimeMillis();
    	 long Finish;
    	 int time;
    	 
    	 Spell spell = new Spell();
         /**if (args.length != 2 ) {
            System.out.println("Usage: spell dictionaryFile.txt inputFile.txt ");
            System.exit(0);
         }*/
         
         BufferedInputStream dict = null , file = null;
         FileWordRead reader;
         
         try{
           
            //dict  = new BufferedInputStream(new FileInputStream(args[0]));
            //file  = new BufferedInputStream(new FileInputStream(args[1]));
	    // To read from specific files, comment the 2 lines above and 
            // uncomment 2 lines below 
          dict  = new BufferedInputStream(new FileInputStream("/Users/Ibrahim/Documents/DataStructures/Spell/src/dictionary.txt"));
	      file  = new BufferedInputStream(new FileInputStream("/Users/Ibrahim/Documents/DataStructures/Spell/src/d1.txt"));
	      }
	      
	      catch(IOException e){ // catch exceptions caused by file input/output errors
	            System.out.println("Check your file name");
	            System.exit(0);
	        }  
         
	      reader = new FileWordRead(dict);
			
	      while (reader.hasNextWord()) {
				try{
					dictionary.insert(reader.nextWord().toLowerCase());
				} catch(DictionaryException e) {
					e.printStackTrace();
			}
		}
			
	      reader = new FileWordRead(file);
	      String word = " ";
	      LinkedList<String> suggestion = new LinkedList<String>();
	      Iterator<String> iters;
	      boolean initial = true;
			
			while (reader.hasNextWord()) {
				
				word = reader.nextWord().toLowerCase();
				if (!dictionary.find(word)) {
					initial = true;
					
					suggestion = new LinkedList<String>();
					System.out.print(word + " => ");
					
					suggestion = LetterSubstitution(word, suggestion);
					suggestion = LetterOmission(word, suggestion);
					suggestion = LetterInsertion(word, suggestion);
					suggestion = LetterReversal(word, suggestion);
					
					iters = suggestion.iterator();
					
					if (!suggestion.isEmpty()) {
						while (iters.hasNext()) {
							if (initial) { 
								System.out.print(iters.next());
								initial = false;
							} else {
								System.out.print(", " + iters.next());
							}
						}
					} else {
						System.out.print("Could not find word in dictionary");
					}
					System.out.println("");
				} 
			}
			
			Finish = System.currentTimeMillis();
			time = (int) (Finish - Start);
			
			System.out.println("\nProgram took " + time + "ms to complete.");
			
	    
	}
       
}
