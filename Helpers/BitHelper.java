package Helpers;

public class BitHelper{
    public static int clearBit(int num, int index){
        return num & ~(1 << index);
    }

    public static int clearBitsMSBThroughIndex(int num, int index){
        return num & ~((-1 >> index) << index);
    }

    public static int clearBitsThrough(int num, int index){
        return num & ((-1 >>> (index + 1)) << (index + 1));
    }

    public static int clearBitsBetween(int num, int i, int j){
        int first = i <= j ? i : j;
        int second = i <= j ? j : i;
        
        int clean_right = (-1 >>> first) << first;
        int clean_left = (-1 << (31 - second)) >>> (31 - second);
        int range = ~(clean_left & clean_right);
        return num & range;
    }

    public static boolean getBit(int num, int index){
        return (num & (1 << index)) != 0;
    }

    public static int setBit(int num, int index){
        return num | (1 << index);
    }

    public static int updateBit(int num, int index, boolean value){
        if(value){
            return setBit(num, index);
        }else{
            return clearBit(num, index);
        }
    }
}