package DataStructures;

/**
 * LinkedList
 */
public class MyLinkedList<E> {

    public Node<E> head;
    public Node<E> tail;
    public int size = 0;
    /**
     * define whatever you want
     */
    public void insert(E item){
        size++;
        if (head == null){
            head = new Node<>(item);
            tail = head;
        }else{
            Node<E> n = new Node<>(item);
            tail.next = n;
            tail = n;
        }
    }

    public void insert_before(E item){
        size++;
        if(head == null){
            head = new Node<>(item);
            tail = head;
        }else{
            Node<E> n = new Node<>(item);
            n.next = head;
            head = n;
        }
    }

    public void insert(Node<E> item){
        size++;
        if (head == null){
            head = item;
            tail = head;
        }else{
            tail.next = item;
            tail = item;
        }
    }

    public void insert_before(Node<E> item){
        size++;
        if(head == null){
            head = item;
            tail = head;
        }else{
            item.next = head;
            head = item;
        }
    }
}