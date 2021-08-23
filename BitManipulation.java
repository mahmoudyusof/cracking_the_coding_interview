import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import Helpers.*;
import java.util.*;

public class BitManipulation {
    @Test
    public void testBitClears() {
        int num = 31;
        assertEquals(31 - 4, BitHelper.clearBit(num, 2));
        assertEquals(3, BitHelper.clearBitsMSBThroughIndex(num, 2));
        assertEquals(31 - 7, BitHelper.clearBitsThrough(num, 2));
        assertEquals(31 - 14, BitHelper.clearBitsBetween(num, 1, 3));
    }

    public static int insert(int original, int insertion, int i, int j) {
        original = BitHelper.clearBitsBetween(original, i, j);
        return original | (insertion << i);
    }

    @Test
    public void testInsert() {
        int original = Integer.parseInt("1010", 16); // 0001 0000 0001 0000
        int insertion = Integer.parseInt("00F0", 16); // 0000 0000 1111 0000
        int output = insert(original, insertion, 0, 8); // 0001 0000 1111 0000
        assertEquals(Integer.parseInt("10F0", 16), output);
    }

    // 5.5 The code checks if the n is power of two (only one bit is high (one hot))

    public static String doubleToBinaryString(double number) {
        if (number >= 1 || number <= 0) {
            return "ERROR";
        }

        StringBuilder result = new StringBuilder();
        result.append(".");
        while (number > 0) {
            if (result.length() >= 32) {
                return "ERROR";
            }
            number = number * 2;
            if (number >= 1) {
                result.append("1");
                number -= 1;
            } else {
                result.append("0");
            }
        }
        return result.toString();
    }

    @Test
    public void testDoubleToBinaryString() {
        assertEquals("ERROR", doubleToBinaryString(-0.33));
        assertEquals("ERROR", doubleToBinaryString(1.33));
        assertEquals("ERROR", doubleToBinaryString(0.64));
        assertEquals(".01", doubleToBinaryString(0.25));
    }

    public static int getLongestSequence(int number) {
        if (~number == 0)
            return Integer.BYTES * 8;

        int current_length = 0;
        int previous_length = 0;
        int max_length = 1;
        while (number != 0) {
            if ((number & 1) == 1) {
                current_length++;
            } else if ((number & 1) == 0) {
                previous_length = (number & 2) == 0 ? 0 : current_length;
                current_length = 0;
            }
            max_length = Math.max(previous_length + current_length + 1, max_length);
            number >>>= 1;
        }
        return max_length;
    }

    @Test
    public void testGetLongestSequence() {
        assertEquals(8, getLongestSequence(0b11011101111));
        assertEquals(3, getLongestSequence(0b10010100100));
        assertEquals(5, getLongestSequence(0b11011));
    }

    public static int getNext(int number) {
        int temp = number;
        int c0 = 0;
        int c1 = 0;
        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        return number + (1 << c0) + (1 << (c1 - 1)) - 1;
    }

    public static int getPrev(int number){
        int temp = number;
        int c0 = 0;
        int c1 = 0;
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }
        if (temp == 0) return -1;
        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        return number - (1 << c1) - (1 << (c0 - 1)) + 1;
    }

    @Test
    public void testGetNextAndGetPrev(){
        assertEquals(0b11011010001111, getNext(13948));
        assertEquals(0b11011001111010, getPrev(13948));
    }
}
