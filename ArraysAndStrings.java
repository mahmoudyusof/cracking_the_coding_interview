import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import Helpers.*;

import org.junit.jupiter.api.Test;

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

    @Test
    public void testIsUnique() {
        String all_unique_chars = "abcdefg";
        String not_all_unique_chars = "aabcdeee";
        assertTrue(isUnique(all_unique_chars));
        assertFalse(isUnique(not_all_unique_chars));
        assertTrue(isUniqueBitVector(all_unique_chars));
        assertFalse(isUnique(not_all_unique_chars));
    }

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
        HashMap<Character, Integer> source_map = new HashMap<>();
        HashMap<Character, Integer> target_map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            source_map.put(s.charAt(i), source_map.containsKey(s.charAt(i)) ? source_map.get(s.charAt(i)) + 1 : 1);
            target_map.put(t.charAt(i), target_map.containsKey(t.charAt(i)) ? target_map.get(t.charAt(i)) + 1 : 1);
        }

        return source_map.equals(target_map);
    }

    @Test
    public void testIsPermutation() {
        String anchor_text = "Mahmoud Youssef";
        String permutation = "MY ahmoudoussef";
        String not_permutation = "some other guy";

        assertTrue(isPermutation(anchor_text, permutation));
        assertFalse(isPermutation(anchor_text, not_permutation));
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

    @Test
    public void testUrlify() {
        char[] spaced = new char[] { 'g', 'o', 'o', 'd', ' ', 'l', 'i', 'n', 'k', ' ', ' ' };
        int size = spaced.length;
        urlify(spaced, size);
        assertEquals(spaced[4], '%');
        assertEquals(spaced[5], '2');
        assertEquals(spaced[6], '0');
    }
    /////////////////////////// END urlify ///////////////////////////////

    public static boolean isPalindromPermutation(String s) {
        BitSet flags = new BitSet(128); // this size works for ascii
        for (int i = 0; i < s.length(); i++) {
            flags.set((int) s.charAt(i), !flags.get((int) s.charAt(i))); // just invert the bit at this location
        }
        return flags.cardinality() < 2; // more than one unique character means it is not a permutation of a palindrome
    }

    @Test
    public void testIsPalindromPermutatoin() {
        String palindrom_permutation = "carrace";
        String not_palindrom_permutation = "notaracecar";

        assertTrue(isPalindromPermutation(palindrom_permutation));
        assertFalse(isPalindromPermutation(not_palindrom_permutation));
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

    @Test
    public void testCheckEdit() {
        String anchor = "Mahmoud Youssef";
        String same = "Mahmoud Youssef";
        String one_edit_away = "Mahmoud Youssuf";
        String different = "another dude";

        assertTrue(checkEdit(anchor, same));
        assertTrue(checkEdit(anchor, one_edit_away));
        assertFalse(checkEdit(anchor, different));
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

    @Test
    public void testCheckAddsOrDeletes() {
        String anchor = "Mahmoud Youssef";
        String same = "Mahmoud Youssef";
        String one_add_away = "Mahmoud Yousseef";
        String one_delete_away = "Mahmoud Yousef";
        String different = "another dude";

        assertTrue(checkAddsOrDeletes(anchor, same));
        assertTrue(checkAddsOrDeletes(one_add_away, anchor));
        assertTrue(checkAddsOrDeletes(anchor, one_delete_away));
        assertFalse(checkAddsOrDeletes(anchor, different));
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

    @Test
    public void testOneOrNoEdits() {
        String anchor = "Mahmoud Youssef";
        String[] one_or_no_edits = new String[] { "Mahmoud Youssef", "Mahmoud Yousef", "Mahmoud Yousseef",
                "Mahmoud Youssuf" };
        String different = "another dude";

        for (String truthy : one_or_no_edits) {
            assertTrue(oneOrNoEdits(anchor, truthy));
        }
        assertFalse(oneOrNoEdits(anchor, different));
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
        sb.append(history);
        sb.append((char) (counter + '0'));
        String newString = sb.toString();
        return newString.length() < s.length() ? newString : s;
    }

    @Test
    public void testCompressString() {
        String repeated = "aaaabbbccd";
        String not_repeated = "abcdefgh";

        assertEquals(String.valueOf("a4b3c2d1"), compressString(repeated));
        assertEquals(not_repeated, compressString(not_repeated));
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

    @Test
    public void testRotate() {
        int[][] mat = new int[][] { 
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 } 
        };

        int[][] rotated = new int[][] {
            { 7, 4, 1 },
            { 8, 5, 2 },
            { 9, 6, 3 } 
        };

        int[][] error_mat = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };

        rotate(mat);
        assertTrue(ArrayHelpers.matrixEqual(mat, rotated));
        assertThrows(IndexOutOfBoundsException.class, () -> rotate(error_mat));
    }
    //////////////////////// END rotate ///////////////////////////////

    public static void broadcastZero(int[][] mat) {
        int i, j;

        int m = mat.length;
        int n = mat[0].length;
        BitSet zeroFlags = new BitSet(m * n);
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (mat[i][j] == 0)
                    zeroFlags.set(i * n + j);
            }
        }

        for (i = 0; i < m * n; i++) {
            if (zeroFlags.get(i)) {
                for (j = 0; j < m; j++) {
                    mat[j][i % n] = 0;
                }
                for (j = 0; j < n; j++) {
                    mat[i / n][j] = 0;
                }

                // i += n - (i%m);

            }
        }
    }

    @Test
    public void testBroadcastZero() {
        int[][] mat = new int[][] {
            {1, 2, 3},
            {0, 5, 6},
            {7, 8, 0}
        };

        int[][] zeroed = new int[][]{
            {0, 2, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        broadcastZero(mat);
        assertTrue(ArrayHelpers.matrixEqual(mat, zeroed));
    }
}