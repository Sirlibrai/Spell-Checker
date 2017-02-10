
public class StringHashCode implements HashCode
{
    //function to get the polynomial accumulation of a string
    private int GetPolyAc(String string) 
    {
        int Polynomial = string.codePointAt(string.length()-1);
        
        int i = string.length() - 2;
        
        while(i >= 0)
        {
            Polynomial = Polynomial * 33 + string.codePointAt(i);     
            i--;
        }
        return Polynomial;              
    }
//cast the object into a string, Get and return the Polynomial Accumulation
    public int giveCode(Object key)
    {
        String string = (String) key;
        int code = GetPolyAc(string);
        return code;
    }
}