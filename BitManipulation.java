import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import Helpers.*;

public class BitManipulation {
    @Test
    public void testBitClears(){
        int num = 31;
        assertEquals(31-4, BitHelper.clearBit(num, 2));
        assertEquals(3, BitHelper.clearBitsMSBThroughIndex(num, 2));
        assertEquals(31-7, BitHelper.clearBitsThrough(num, 2));
        assertEquals(31-14, BitHelper.clearBitsBetween(num, 1, 3));
    }

    public static int insert(int original, int insertion, int i, int j){
        original = BitHelper.clearBitsBetween(original, i, j);
        return original | (insertion << i);
    }

    @Test
    public void testInsert(){
        int original = Integer.parseInt("1010", 16); // 0001 0000 0001 0000
        int insertion = Integer.parseInt("00F0", 16); // 0000 0000 1111 0000
        int output = insert(original, insertion, 0, 8); // 0001 0000 1111 0000
        assertEquals(Integer.parseInt("10F0", 16), output);
    }

    // 5.5 The code checks if the n is power of two (only one bit is high (one hot))
}
