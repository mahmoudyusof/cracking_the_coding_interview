package Helpers;

import DataStructures.Lists.MyLinkedList;

public class SumReturn {
    public MyLinkedList<Integer> ll;
    public int carry;

    public SumReturn(boolean c, MyLinkedList<Integer> partial){
        ll = partial;
        carry = c ? 1 : 0;
    }
}
