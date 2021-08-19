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
}
