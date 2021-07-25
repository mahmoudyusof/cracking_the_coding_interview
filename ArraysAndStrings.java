import java.util.*;

/**
 * ArraysAndStrings
 */
public class ArraysAndStrings {

    /**
     * isUnique problem solution
     * 
     * @param s string to be tested
     * @return true if the string has only unique characters
     */
    public static boolean isUnique(String s) {
        HashSet<Character> characterSet = new HashSet<>(128); // can scale up if needed for unicode
        for (int i = 0; i < s.length(); i++) {
            if (characterSet.contains(s.charAt(i))) {
                return false;
            } else {
                characterSet.add(s.charAt(i));
            }
        }
        return true;
    }

    public static boolean isUniqueBitVector(String s) {
        BitSet flags = new BitSet(128); // only valid for ascii
        for (int i = 0; i < s.length(); i++) {
            if (flags.get((int) s.charAt(i))) {
                return false;
            } else {
                flags.set((int) s.charAt(i));
            }
        }
        return true;
    }
    ////////////////////////// END ISUNIQUE ////////////////////////////////

    /**
     * check if string s is a permutation of string t
     * 
     * @param s
     * @param t
     * @return true if s is permutation of t
     */
    public static boolean isPermutation(String s, String t) {
        if (s.length() != t.length())
            return false;
        // could be done in O(n^2) simply, but that is really bad
        // another solution would be to sort both of them then compare O(nlog(n))
        // frequency count takes O(n) for each of them
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> tmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            smap.put(s.charAt(i), smap.containsKey(s.charAt(i)) ? smap.get(s.charAt(i)) + 1 : 1);
            tmap.put(t.charAt(i), tmap.containsKey(t.charAt(i)) ? tmap.get(t.charAt(i)) + 1 : 1);
        }

        return smap.equals(tmap);
    }
    ////////////////////// END isPermutation ///////////////////////////////

    public static void urlify(char[] s, int length) {
        int cursor = length - 1;
        boolean edge = true;
        for (int i = length - 1; i >= 0; i--) {
            if (s[i] == ' ') {
                if (edge)
                    continue;
                s[cursor] = '0';
                s[cursor - 1] = '2';
                s[cursor - 2] = '%';
                cursor -= 3;
            } else {
                edge = false;
                s[cursor] = s[i];
                cursor--;
            }
        }
    }
    /////////////////////////// END urlify ///////////////////////////////

    public static boolean isPalindromPermutation(String s) {
        BitSet flags = new BitSet(128); // this size works for ascii
        for (int i = 0; i < s.length(); i++) {
            flags.set((int) s.charAt(i), !flags.get((int) s.charAt(i))); // just invert the bit at this location
        }
        return flags.cardinality() < 2; // more than one unique character means it is not a permutation of a palindrome
    }
    //////////////////// END isPalindromePermutation //////////////////////

    public static boolean checkEdit(String s, String t) {
        // will make sure this function only gets equally sized strings
        boolean foundOne = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (foundOne)
                    return false;
                else
                    foundOne = true;
            }
        }
        return true;
    }

    public static boolean checkAddsOrDeletes(String s, String t) {
        // will make sure that s is always one character longer than t
        int stringsOffset = 0; // becomes 1 when we find a difference for the first time
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(i + stringsOffset) != t.charAt(i)) {
                if (stringsOffset == 1)
                    return false;
                else
                    stringsOffset = 1;
                i--;
            }
        }
        return true;
    }

    public static boolean oneOrNoEdits(String s, String t) {
        if (s.length() == t.length())
            return checkEdit(s, t);
        else if (s.length() - t.length() == 1)
            return checkAddsOrDeletes(s, t);
        else if (s.length() - t.length() == -1) {
            return checkAddsOrDeletes(t, s);
        } else
            return false;
    }
    ////////////////////////// END oneOrNoEdits ////////////////////////////

    public static String compressString(String s) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        Character history = null;
        for (int i = 0; i < s.length(); i++) {
            if (history == null) {
                history = s.charAt(i);
                counter = 1;
                continue;
            }
            if (s.charAt(i) == history) {
                counter++;
            } else {
                sb.append(history);
                sb.append((char) (counter + '0'));
                history = s.charAt(i);
                counter = 1;
            }
        }
        String newString = sb.toString();
        return newString.length() < s.length() ? newString : s;
    }
    ////////////////////////// END compressString ///////////////////////////

    public static void rotate(int[][] mat) throws IndexOutOfBoundsException {
        if (mat.length != mat[0].length)
            throw new IndexOutOfBoundsException("shape is not correct, provide a square matrix");
        int n = mat.length;
        int temp;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;

                temp = mat[first][i];
                mat[first][i] = mat[last - offset][first];
                mat[last - offset][first] = mat[last][last - offset];
                mat[last][last - offset] = mat[i][last];
                mat[i][last] = temp;
            }
        }

    }
    //////////////////////// END rotate ///////////////////////////////

    public static void zero(int[][] mat){
        int i, j;
        try{

            int m = mat.length;
            int n = mat[0].length;
            BitSet zeroFlags = new BitSet(m*n);
            for(i=0; i<m; i++){
                for(j=0; j<n; j++){
                    if(mat[i][j] == 0) zeroFlags.set(i*n + j);
                }
            }
    
            for(i=0; i<m*n; i++){
                if(zeroFlags.get(i)){
                    for(j=0; j<m; j++){
                        mat[j][i%n] = 0;
                    }
                    for(j=0; j<n; j++){
                        mat[i/n][j] = 0;
                    }
    
                    // i += n - (i%m);
                    
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // test my code if you dare!

        // System.out.println(isPermutation("abcd", "cdab")); // true
        // System.out.println(isPermutation("abcd", "cddb")); // false

        // char[] url = new char[] {'s', 'o', 'm', 'e', ' ', 'l', 'i', 'n', 'k', ' ', '
        // '};
        // for(char c : url){
        // System.out.print(c);
        // }
        // System.out.println("\n==================");
        // urlify(url, 11);
        // for(char c : url){
        // System.out.print(c);
        // }
        // System.out.println("\n==================");

        // System.out.println(isPalindromPermutation("racecar")); // true
        // System.out.println(isPalindromPermutation("raarcec")); // true
        // System.out.println(isPalindromPermutation("this ain't no true")); // false

        // System.out.println(oneOrNoEdits("true", "tru"));
        // System.out.println(oneOrNoEdits("true", "truer"));
        // System.out.println(oneOrNoEdits("true", "tre"));
        // System.out.println(oneOrNoEdits("true", "trrue"));
        // System.out.println(oneOrNoEdits("true", "rue"));
        // System.out.println(oneOrNoEdits("true", "rtrue"));
        // System.out.println(oneOrNoEdits("true", "trul"));
        // System.out.println(oneOrNoEdits("true", "trul"));
        // System.out.println(oneOrNoEdits("true", "rrue"));
        // System.out.println(oneOrNoEdits("true", "true"));

        // System.out.println(oneOrNoEdits("false", "falsify"));
        // System.out.println(oneOrNoEdits("false", "salfe"));
        // System.out.println(oneOrNoEdits("false", "falssy"));

        // System.out.println(compressString("hello rrreepppeeeeaaatttttttt"));
        // System.out.println(compressString("every character is unlike the one before
        // it"));

        // int[][] mat = new int[5][5];
        // mat[0] = new int[] { 0, 1, 2, 3, 4 };
        // mat[1] = new int[] { 5, 6, 7, 8, 9 };
        // mat[2] = new int[] { 0, 1, 2, 3, 4 };
        // mat[3] = new int[] { 5, 6, 7, 8, 9 };
        // mat[4] = new int[] { 0, 1, 2, 3, 4 };

        // for (int[] row : mat) {
        //     for (int item : row) {
        //         System.out.print(item);
        //         System.out.print(", ");
        //     }
        //     System.out.println(" ");
        // }
        // rotate(mat);
        // System.out.println("============================");
        // for (int[] row : mat) {
        //     for (int item : row) {
        //         System.out.print(item);
        //         System.out.print(", ");
        //     }
        //     System.out.println(" ");
        // }

        int[][] mat = new int[4][5];
        mat[0] = new int[] { 4, 1, 2, 3, 4 };
        mat[1] = new int[] { 5, 0, 7, 0, 9 };
        mat[2] = new int[] { 4, 1, 2, 0, 4 };
        mat[3] = new int[] { 5, 6, 0, 8, 9 };

        for (int[] row : mat) {
            for (int item : row) {
                System.out.print(item);
                System.out.print(", ");
            }
            System.out.println(" ");
        }
        zero(mat);
        System.out.println("============================");
        for (int[] row : mat) {
            for (int item : row) {
                System.out.print(item);
                System.out.print(", ");
            }
            System.out.println(" ");
        }
    }
}