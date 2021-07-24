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

    public static boolean isPermutation(String s, String t){
        if(s.length() != t.length()) return false;
        // could be done in O(n^2) simply, but that is really bad
        // another solution would be to sort both of them then compare O(nlog(n))
        // frequency count takes O(n) for each of them
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> tmap = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            smap.put(s.charAt(i), smap.containsKey(s.charAt(i)) ? smap.get(s.charAt(i)) + 1 : 1);
            tmap.put(t.charAt(i), tmap.containsKey(t.charAt(i)) ? tmap.get(t.charAt(i)) + 1 : 1);
        }

        return smap.equals(tmap);
    }
    public static void main(String[] args) {
        // test my code if you dare!
        // System.out.println(isPermutation("abcd", "cdab"));
        // System.out.println(isPermutation("abcd", "cddb"));
    }
}