
import java.io.BufferedInputStream;
import java.io.File;

public class FileWordRead {
    private BufferedInputStream in;
    private String nextWord;
    private StringBuffer buf;
    private boolean endOfFile;

    public FileWordRead(BufferedInputStream inFile) throws java.io.IOException
    {
    	in = inFile;
        endOfFile = false;
        nextWord  = readWord(); 
    }

    private String readWord() throws java.io.IOException {
    	
        int ch;
        char nextChar;
        StringBuffer buf = new StringBuffer();

		ch = in.read();
		if ( ch == -1 ){            
		    endOfFile = true;
		    return(null);
		}

        nextChar = Character.toLowerCase((char) ch);
        while ( ! (nextChar >= 'a' && nextChar <= 'z' )){
		    ch = in.read();
		    if ( ch == -1 ){            
				endOfFile = true;
				return(null);
		    }
		    nextChar = Character.toLowerCase((char) ch);
        }

		while ( nextChar >= 'a' && nextChar <= 'z' ){
	        buf.append(nextChar);  
		    ch = in.read();
		    if ( ch == -1 ){            
				endOfFile = true;
				return(buf.toString());
		    }
	
	        nextChar = Character.toLowerCase((char) ch);
		}
		
	    return (buf.toString());
    }

    public boolean hasNextWord() {
       if ( nextWord != null ) 
    	   return(true);
       else 
    	   return(false);
    }

    public String nextWord() throws java.io.IOException{
        String toReturn = nextWord;
        if ( !endOfFile ) 
        	nextWord = readWord();
        else 
        	nextWord = null;
        
        return(toReturn);
    }
}


