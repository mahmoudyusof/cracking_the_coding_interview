import java.util.*;

import DataStructures.MyLinkedList;
import DataStructures.Node;

public class LinkedLists {
    public static void removeDups(LinkedList<Integer> ll){
        // this is the O(n) solution
        // without any buffer it would take up to O(n^2)
        HashSet<Integer> seen = new HashSet<>();
        Iterator<Integer> iter = ll.iterator();
        while(iter.hasNext()){
            int current = iter.next();
            if (seen.contains(current)){
                iter.remove();
            }
        }
    }

    public static void testRemoveDups(){
        Integer[] arr1 = new Integer[] {1, 2, 3, 4, 5};
        Integer[] arr2 = new Integer[] {1, 1, 1, 4, 5};
        Integer[] arr3 = new Integer[] {1, 2, 3, 1, 5};
        LinkedList<Integer> l1 = new LinkedList<Integer>(Arrays.asList(arr1));
        LinkedList<Integer> l2 = new LinkedList<Integer>(Arrays.asList(arr2));
        LinkedList<Integer> l3 = new LinkedList<Integer>(Arrays.asList(arr3));

        removeDups(l1);
        removeDups(l2);
        removeDups(l3);

        assert l1.size() == 5;
        assert l2.size() == 3;
        assert l3.size() == 4;
    }


    public static Integer kthToLastElement(LinkedList<Integer> ll, Integer k) throws IndexOutOfBoundsException{
        // assuming we don't know the length
        // we can get the length first, then use it
        Iterator<Integer> iter = ll.iterator();
        int len = 0;
        while(iter.hasNext()){
            len++;
            iter.next();
        }
        // assuming the list must be larger than or as large as k
        // which means I am too lazy to validate this
        int i = 0;
        while(iter.hasNext()){
            if(i == len - k){
                return iter.next();
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public static void testKthToLastElement(){
        Integer[] arr1 = new Integer[] {1, 2, 3, 4, 5};
        Integer[] arr2 = new Integer[] {1, 1, 1, 4, 5};
        Integer[] arr3 = new Integer[] {1, 2, 3, 1, 5};
        LinkedList<Integer> l1 = new LinkedList<Integer>(Arrays.asList(arr1));
        LinkedList<Integer> l2 = new LinkedList<Integer>(Arrays.asList(arr2));
        LinkedList<Integer> l3 = new LinkedList<Integer>(Arrays.asList(arr3));

        assert kthToLastElement(l1, 0) == 5;
        assert kthToLastElement(l2, 1) == 4;
        assert kthToLastElement(l3, 2) == 3;
    }

    public static void removeNode(MyLinkedList<Integer> ll, Node<Integer> node){
        Node<Integer> prev = ll.head;
        while(prev.next != null){
            Node<Integer> current = prev.next;
            if(current == node){
                prev.next = current.next;
            }else{
                prev = current;
            }
        }
    }

    public static void testRemoveNode(){
        MyLinkedList<Integer> l1 = new MyLinkedList<>();
        for(int i=0; i<6; i++){
            l1.insert(i);
        }
        Node<Integer> n = l1.head.next.next; // node with value 2
        removeNode(l1, n);

        Node<Integer> current = l1.head;
        do{
            assert current.value != 2;
            current = current.next;
        }while(current != null);

    }


    public static void main(String[] args) {
        // test me here
        testRemoveDups();
        testKthToLastElement();
        testRemoveNode();
    }
}
