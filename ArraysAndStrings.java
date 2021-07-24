import java.util.*;

/**
 * ArraysAndStrings
 */
public class ArraysAndStrings {

    /**
     * isUnique problem solution
     * @param s string to be tested
     * @return true if the string has only unique characters
     */
    public static boolean isUnique(String s){
        HashSet<Character> characterSet = new HashSet<>(128); // can scale up if needed for unicode
        for(int i=0; i<s.length(); i++){
            if(characterSet.contains(s.charAt(i))){
                return false;
            }else{
                characterSet.add(s.charAt(i));
            }
        }
        return true;
    }

    public static boolean isUniqueBitVector(String s){
        BitSet flags = new BitSet(128); // only valid for ascii
        for(int i=0; i<s.length(); i++){
            if(flags.get((int) s.charAt(i))){
                return false;
            }else{
                flags.set((int) s.charAt(i));
            }
        }
        return true;
    }
    //////////////////////////  END ISUNIQUE ////////////////////////////////

    
    public static void main(String[] args) {
        // test my code if you dare!
    }
}