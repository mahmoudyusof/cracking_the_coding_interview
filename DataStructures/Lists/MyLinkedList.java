package DataStructures.Lists;

/**
 * 
 */
public class MyLinkedList<T extends Comparable<T>> {

    public Node<T> head;
    public Node<T> tail;
    public int size = 0;

    /**
     * Inserts an item at the tail of the list
     * 
     * @param item of type T (value of added item)
     */
    public void insert(T item) {
        size++;
        if (head == null) {
            head = new Node<>(item);
            tail = head;
        } else {
            Node<T> n = new Node<>(item);
            tail.next = n;
            tail = n;
        }
    }

    /**
     * Inserts an item at the tail of the list
     * 
     * @param item of type Node<T> (added item)
     */
    public void insert(Node<T> item) {
        size++;
        if (head == null) {
            head = item;
            tail = head;
        } else {
            tail.next = item;
            tail = item;
        }
    }

    /**
     * Inserts an item at the head of the list
     * 
     * @param item of type T (value of added item)
     */
    public void insert_before(T item) {
        size++;
        if (head == null) {
            head = new Node<>(item);
            tail = head;
        } else {
            Node<T> n = new Node<>(item);
            n.next = head;
            head = n;
        }
    }

    /**
     * Inserts an item at the head of the list
     * 
     * @param item of type Node<T> (added item)
     */
    public void insert_before(Node<T> item) {
        size++;
        if (head == null) {
            head = item;
            tail = head;
        } else {
            item.next = head;
            head = item;
        }
    }
}