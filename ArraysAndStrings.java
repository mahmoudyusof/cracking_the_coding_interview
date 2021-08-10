import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import Helpers.*;

import org.junit.jupiter.api.Test;

public class ArraysAndStrings {

    /**
     * isUnique problem solution
     * 
     * @param str string to be tested
     * @return true if the string has only unique characters
     */
    public static boolean isUnique(String str) {
        HashSet<Character> characterSet = new HashSet<>(128); // can scale up if needed for unicode
        for (int i = 0; i < str.length(); i++) {
            if (characterSet.contains(str.charAt(i))) {
                return false;
            } else {
                characterSet.add(str.charAt(i));
            }
        }
        return true;
    }

    public static boolean isUniqueBitVector(String str) {
        BitSet flags = new BitSet(128); // only valid for ascii
        for (int i = 0; i < str.length(); i++) {
            if (flags.get((int) str.charAt(i))) {
                return false;
            } else {
                flags.set((int) str.charAt(i));
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
     * @param source
     * @param t
     * @return true if s is permutation of t
     */
    public static boolean isPermutation(String source, String target) {
        if (source.length() != target.length())
            return false;
        // could be done in O(n^2) simply, but that is really bad
        // another solution would be to sort both of them then compare O(nlog(n))
        // frequency count takes O(n) for each of them
        HashMap<Character, Integer> source_map = new HashMap<>();
        HashMap<Character, Integer> target_map = new HashMap<>();

        for (int i = 0; i < source.length(); i++) {
            source_map.put(source.charAt(i),
                    source_map.containsKey(source.charAt(i)) ? source_map.get(source.charAt(i)) + 1 : 1);
            target_map.put(target.charAt(i),
                    target_map.containsKey(target.charAt(i)) ? target_map.get(target.charAt(i)) + 1 : 1);
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

    /**
     * replaces empty spaces with a url encoded version %20
     * takes the length of the string after modification and the string itself has extra
     * spaces to acount for the added characters
     * @param str
     * @param length
     */
    public static void urlify(char[] str, int length) {
        int cursor = length - 1;
        boolean edge = true;
        for (int i = length - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                if (edge)
                    continue;
                str[cursor] = '0';
                str[cursor - 1] = '2';
                str[cursor - 2] = '%';
                cursor -= 3;
            } else {
                edge = false;
                str[cursor] = str[i];
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


    /**
     * checks if a string is a permutation of a palindrome
     * @param str
     * @return
     */
    public static boolean isPalindromPermutation(String str) {
        BitSet flags = new BitSet(128); // this size works for ascii
        for (int i = 0; i < str.length(); i++) {
            flags.set((int) str.charAt(i), !flags.get((int) str.charAt(i))); // just invert the bit at this location
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

    /**
     * takes two strings and returns true if they are one edit away or the same
     * an edit means one character is changed, not removed, not added, changed
     * @param source
     * @param target
     * @return
     */
    public static boolean checkEdit(String source, String target) {
        // will make sure this function only gets equally sized string
        boolean foundOne = false;
        for (int i = 0; i < source.length(); i++) {
            if (source.charAt(i) != target.charAt(i)) {
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

    /**
     * takes two strings and returns true if one string is one character away from the other
     * @param source
     * @param target
     * @return
     */
    public static boolean checkAddsOrDeletes(String source, String target) {
        // will make sure that s is always one character longer than t
        int stringsOffset = 0; // becomes 1 when we find a difference for the first time
        for (int i = 0; i < target.length(); i++) {
            if (source.charAt(i + stringsOffset) != target.charAt(i)) {
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

    /**
     * takes two strings and returns true if the strings is one edit distance away from one another
     * @param source
     * @param target
     * @return
     */
    public static boolean oneOrNoEdits(String source, String target) {
        if (source.length() == target.length())
            return checkEdit(source, target);
        else if (source.length() - target.length() == 1)
            return checkAddsOrDeletes(source, target);
        else if (source.length() - target.length() == -1) {
            return checkAddsOrDeletes(target, source);
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

    /**
     * replaces repeated subsequent characters with only one character then the frequency
     * @param str
     * @return
     */
    public static String compressString(String str) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        Character history = null;
        for (int i = 0; i < str.length(); i++) {
            if (history == null) {
                history = str.charAt(i);
                counter = 1;
                continue;
            }
            if (str.charAt(i) == history) {
                counter++;
            } else {
                sb.append(history);
                sb.append(Integer.toString(counter));
                history = str.charAt(i);
                counter = 1;
            }
        }
        sb.append(history);
        sb.append(Integer.toString(counter));
        String newString = sb.toString();
        return newString.length() < str.length() ? newString : str;
    }

    @Test
    public void testCompressString() {
        String repeated = "aaaabbbccd";
        String not_repeated = "abcdefgh";
        String more_than_nine = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbb";

        assertEquals(String.valueOf("a4b3c2d1"), compressString(repeated));
        assertEquals(not_repeated, compressString(not_repeated));
        assertEquals("a32b19", compressString(more_than_nine));
    }

    /**
     * rotates a matrix clock wise by 90 degrees
     * @param mat
     * @throws IndexOutOfBoundsException
     */
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
        int[][] mat = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        int[][] rotated = new int[][] { { 7, 4, 1 }, { 8, 5, 2 }, { 9, 6, 3 } };

        int[][] error_mat = new int[][] { { 1, 2, 3 }, { 4, 5, 6 } };

        rotate(mat);
        assertTrue(ArrayHelpers.matrixEqual(mat, rotated));
        assertThrows(IndexOutOfBoundsException.class, () -> rotate(error_mat));
    }
    
    /**
     * for each element in the matrix, if it is zero, the function is going to set
     * its row and column to zeros
     * @param mat
     */
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
        int[][] mat = new int[][] { { 1, 2, 3 }, { 0, 5, 6 }, { 7, 8, 0 } };

        int[][] zeroed = new int[][] { { 0, 2, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

        broadcastZero(mat);
        assertTrue(ArrayHelpers.matrixEqual(mat, zeroed));
    }
}