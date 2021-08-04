import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import DataStructures.Lists.*;
import Helpers.SumReturn;

public class LinkedLists {
    /**
     * removes duplicated items in a linked list
     * 
     * @param linked_list
     */
    public static void removeDups(LinkedList<Integer> linked_list) {
        // this is the O(n) solution
        // without any buffer it would take up to O(n^2)
        HashSet<Integer> seen = new HashSet<>();
        Iterator<Integer> iter = linked_list.iterator();
        while (iter.hasNext()) {
            int current = iter.next();
            if (seen.contains(current)) {
                iter.remove();
            } else {
                seen.add(current);
            }
        }
    }

    @Test
    public void testRemoveDups() {
        Integer[] arr1 = new Integer[] { 1, 2, 3, 4, 5 };
        Integer[] arr2 = new Integer[] { 1, 1, 1, 4, 5 };
        Integer[] arr3 = new Integer[] { 1, 2, 3, 1, 5 };
        LinkedList<Integer> l1 = new LinkedList<Integer>(Arrays.asList(arr1));
        LinkedList<Integer> l2 = new LinkedList<Integer>(Arrays.asList(arr2));
        LinkedList<Integer> l3 = new LinkedList<Integer>(Arrays.asList(arr3));

        removeDups(l1);
        removeDups(l2);
        removeDups(l3);
        assertEquals(5, l1.size());
        assertEquals(3, l2.size());
        assertEquals(4, l3.size());
    }

    /**
     * returns the kth to last element of a single linked list
     * 
     * @param linked_list
     * @param k
     * @return value of kth to last element
     * @throws IndexOutOfBoundsException if k is not within the range of the list
     */
    public static int kthToLastElement(LinkedList<Integer> linked_list, Integer k) throws IndexOutOfBoundsException {
        // assuming we don't know the length
        // we can get the length first, then use it
        Iterator<Integer> iter = linked_list.iterator();
        int len = 0;
        while (iter.hasNext()) {
            len++;
            iter.next();
        }
        // assuming the list must be larger than or as large as k
        // which means I am too lazy to validate this
        int i = 1;
        iter = linked_list.iterator();
        while (iter.hasNext()) {
            if (i == len - k) {
                return iter.next();
            } else {
                i++;
                iter.next();
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Test
    public void testKthToLastElement() {
        Integer[] arr1 = new Integer[] { 1, 2, 3, 4, 5 };
        Integer[] arr2 = new Integer[] { 1, 1, 1, 4, 5 };
        Integer[] arr3 = new Integer[] { 1, 2, 3, 1, 5 };
        LinkedList<Integer> l1 = new LinkedList<Integer>(Arrays.asList(arr1));
        LinkedList<Integer> l2 = new LinkedList<Integer>(Arrays.asList(arr2));
        LinkedList<Integer> l3 = new LinkedList<Integer>(Arrays.asList(arr3));

        assertEquals(5, kthToLastElement(l1, 0));
        assertEquals(4, kthToLastElement(l2, 1));
        assertEquals(3, kthToLastElement(l3, 2));
    }

    /**
     * removes a node from a single linked list given the node itself
     * 
     * @param linked_list
     * @param node
     */
    public static void removeNode(MyLinkedList<Integer> linked_list, Node<Integer> node) {
        Node<Integer> prev = linked_list.head;
        while (prev.next != null) {
            Node<Integer> current = prev.next;
            if (current == node) {
                prev.next = current.next;
            } else {
                prev = current;
            }
        }
    }

    @Test
    public void testRemoveNode() {
        MyLinkedList<Integer> l1 = new MyLinkedList<>();
        for (int i = 0; i < 6; i++) {
            l1.insert(i);
        }
        Node<Integer> n = l1.head.next.next; // node with value 2
        removeNode(l1, n);

        Node<Integer> current = l1.head;
        do {
            assertNotEquals(Integer.valueOf(2), current.value);
            current = current.next;
        } while (current != null);

    }

    /**
     * takes a linked list and returns a new one where all elements greater than
     * pivot are on one side and all elements less than pivot are on the other side
     * like one step of quick sort
     * 
     * @param linked_list
     * @param pivot
     * @return
     */
    public static MyLinkedList<Integer> partition(MyLinkedList<Integer> linked_list, int pivot) {
        Node<Integer> current = linked_list.head;
        MyLinkedList<Integer> less = new MyLinkedList<>();
        MyLinkedList<Integer> more = new MyLinkedList<>();
        while (current != null) {
            if (current.value < pivot) {
                less.insert(current.value);
            } else {
                more.insert(current.value);
            }
            current = current.next;
        }

        less.tail.next = more.head;
        less.tail = more.tail;
        return less;
    }

    public static void testPartition() {
        MyLinkedList<Integer> ll = new MyLinkedList<>();
        ll.insert(5);
        ll.insert(2);
        ll.insert(7);
        ll.insert(1);
        ll.insert(3);
        ll.insert(8);
        ll.insert(9);
        ll.insert(6);

        MyLinkedList<Integer> newList = partition(ll, 5);
        Node<Integer> current = newList.head;
        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    /**
     * zero pads a linked list from the head side of the list
     * 
     * @param linked_list
     * @param padding_size
     */
    public static void pad(MyLinkedList<Integer> linked_list, int padding_size) {
        for (int i = 0; i < padding_size; i++) {
            linked_list.insert_before(0);
        }
    }

    /**
     * assuming that each linked list represents an integer where each element in
     * the list represents a digit this function creates a new list of the addition
     * value of the linked lists
     * 
     * @param linked_list_one
     * @param linked_list_two
     * @return linked list, the sum of the two linked lists
     */
    public static MyLinkedList<Integer> addLinkedLists(MyLinkedList<Integer> linked_list_one,
            MyLinkedList<Integer> linked_list_two) {
        // the backwards is easy, let's just make the forward one
        // why not just use a bloody doubly linked list instead
        // screw it why not use actual ints
        // oh yeah, big integer and shit, ok
        if (linked_list_one.size < linked_list_two.size) {
            pad(linked_list_one, linked_list_two.size - linked_list_one.size);
        } else if (linked_list_one.size > linked_list_two.size) {
            pad(linked_list_two, linked_list_one.size - linked_list_two.size);
        }
        MyLinkedList<Integer> result = new MyLinkedList<>();
        SumReturn last_value = partialSum(linked_list_one.head, linked_list_two.head, result);

        if (last_value.carry == 1) {
            result.insert_before(1);
        }
        return result;
    }

    /**
     * helper function for addLinkedLists this one is called recursively on specific
     * nodes to add the sub linked list starting from these nodes it returns the
     * SumReturn of the addition of the sub lists and populates the new_linked_list
     * which is passed by reference
     * 
     * @param node_one
     * @param node_two
     * @param new_linked_list
     * @return
     */
    public static SumReturn partialSum(Node<Integer> node_one, Node<Integer> node_two,
            MyLinkedList<Integer> new_linked_list) {
        // padding and synced iteration is going to make both null at the same time
        if (node_one.next == null && node_two.next == null) {
            int actual_sum = node_one.value + node_two.value;
            new_linked_list.insert_before(actual_sum % 10);
            return new SumReturn(actual_sum > 9, new_linked_list);
        }

        SumReturn inner = partialSum(node_one.next, node_two.next, new_linked_list);
        int actual_sum = node_one.value + node_two.value + inner.carry;
        new_linked_list.insert_before(actual_sum % 10);
        return new SumReturn(actual_sum > 9, new_linked_list);
    }

    @Test
    public void testAddLinkedLists() {
        MyLinkedList<Integer> l1 = new MyLinkedList<>();
        MyLinkedList<Integer> l2 = new MyLinkedList<>();

        l1.insert(9);
        l1.insert(2);
        l1.insert(8);

        l2.insert(8);
        l2.insert(5);

        MyLinkedList<Integer> result = addLinkedLists(l1, l2);
        assertEquals(Integer.valueOf(1), result.head.value);
        assertEquals(Integer.valueOf(0), result.head.next.value);
        assertEquals(Integer.valueOf(1), result.head.next.next.value);
        assertEquals(Integer.valueOf(3), result.head.next.next.next.value);

    }

    /**
     * checks if a given linked list represents a palindrom I wish this linked list
     * was a doubly linked list but the author is a moron
     * 
     * @param linked_list
     * @return
     */
    public static boolean isPalindrome(MyLinkedList<Character> linked_list) {
        MyLinkedList<Character> reversed = new MyLinkedList<>();
        Node<Character> current = linked_list.head;
        while (current != null) {
            reversed.insert_before(current.value);
            current = current.next;
        }

        Node<Character> current1 = linked_list.head;
        Node<Character> current2 = reversed.head;
        while (current1 != null) {
            if (current1.value != current2.value) {
                return false;
            } else {
                current1 = current1.next;
                current2 = current2.next;
            }
        }
        return true;
    }

    public static void testIsPalindrome() {
        MyLinkedList<Character> palindrome = new MyLinkedList<>();
        MyLinkedList<Character> not_palindrome = new MyLinkedList<>();

        palindrome.insert('a');
        palindrome.insert('b');
        palindrome.insert('c');
        palindrome.insert('b');
        palindrome.insert('a');

        not_palindrome.insert('a');
        not_palindrome.insert('b');
        not_palindrome.insert('c');

        assertTrue(isPalindrome(palindrome));
        assertFalse(isPalindrome(not_palindrome));
    }

    /**
     * given two linked lists if they are intersecting this function returns the
     * intersection node otherwise it returns null
     * 
     * @param linked_list_one
     * @param linked_list_two
     * @return
     */
    public static Node<Integer> getIntersectionNode(MyLinkedList<Integer> linked_list_one,
            MyLinkedList<Integer> linked_list_two) {
        Node<Integer> tail1 = linked_list_one.head;
        Node<Integer> tail2 = linked_list_two.head;
        int size1 = 0;
        int size2 = 0;

        while (tail1.next != null) {
            tail1 = tail1.next;
            size1++;
        }
        while (tail2.next != null) {
            tail2 = tail2.next;
            size2++;
        }

        if (tail1 != tail2) {
            return null;
        }
        Node<Integer> current1 = linked_list_one.head;
        Node<Integer> current2 = linked_list_two.head;
        if (size1 > size2) {
            for (int i = 0; i < size1 - size2; i++) {
                current1 = current1.next;
            }
        } else if (size2 > size1) {
            for (int i = 0; i < size2 - size1; i++) {
                current1 = current1.next;
            }
        }

        while (current1.next != null) {
            if (current1 == current2) {
                return current1;
            } else {
                current1 = current1.next;
                current2 = current2.next;
            }
        }
        return null;
    }

    @Test
    public void testGetIntersectionNode() {
        Node<Integer> intersection = new Node<>(3);
        MyLinkedList<Integer> i1 = new MyLinkedList<>();
        MyLinkedList<Integer> i2 = new MyLinkedList<>();

        i1.insert(-1);
        i1.insert(0);
        i1.insert(1);
        i1.insert(2);
        i1.insert(intersection);
        i1.insert(4);
        i1.insert(5);

        i2.insert(1);
        i2.insert(2);
        i2.insert(intersection);

        MyLinkedList<Integer> ni1 = new MyLinkedList<>();
        MyLinkedList<Integer> ni2 = new MyLinkedList<>();

        ni1.insert(-1);
        ni1.insert(0);
        ni1.insert(1);
        ni1.insert(2);
        ni1.insert(3);
        ni1.insert(4);
        ni1.insert(5);

        ni2.insert(1);
        ni2.insert(2);
        ni2.insert(3);

        assertEquals(Integer.valueOf(3), getIntersectionNode(i1, i2).value);
        assertNull(getIntersectionNode(ni1, ni2));

    }

    /**
     * given a linked list, this function checks if the linked list has a loop, if
     * it does, it returns the intersection node otherwise it returns null
     * 
     * @param n
     * @return
     */
    public static Node<Integer> getLoopStart(Node<Integer> n) {
        Node<Integer> slow = n.next;
        Node<Integer> fast = n.next.next;
        while (slow != fast) {
            if (slow.next == null || fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = n; // reusing for memory saving and because variable declaration is boring
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    @Test
    public void testGetLoopStart() {
        MyLinkedList<Integer> ll = new MyLinkedList<>();
        Node<Integer> start = new Node<Integer>(4);
        Node<Integer> tail = new Node<Integer>(9);

        ll.insert(0);
        ll.insert(1);
        ll.insert(2);
        ll.insert(3);
        ll.insert(start);
        ll.insert(5);
        ll.insert(6);
        ll.insert(7);
        ll.insert(8);
        ll.insert(tail);

        ll.tail.next = start;

        MyLinkedList<Integer> noloop = new MyLinkedList<>();
        noloop.insert(1);
        noloop.insert(2);
        noloop.insert(3);

        assertEquals(Integer.valueOf(4), getLoopStart(ll.head).value);
        assertNull(getLoopStart(noloop.head));
    }
}
